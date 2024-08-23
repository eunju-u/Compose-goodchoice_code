package com.example.ui.webview

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import android.webkit.WebChromeClient
import androidx.appcompat.app.AlertDialog
import com.google.accompanist.web.AccompanistWebChromeClient
import com.example.ui.R

class WebChromeClient(
    var activity: WebViewActivity,
    var webViewListener: WebViewListener? = null
) : AccompanistWebChromeClient() {

    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        AlertDialog.Builder(view.context).apply {
            setMessage(message)
            setPositiveButton(android.R.string.ok) { _: DialogInterface?, _ -> result.confirm() }
            setCancelable(false)

            if (!activity.isFinishing) show()
        }.create()
        return true
    }

    override fun onJsConfirm(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        AlertDialog.Builder(view.context).apply {
            setMessage(message)
            setPositiveButton(android.R.string.ok) { _: DialogInterface?, _: Int -> result.confirm() }
            setNegativeButton(android.R.string.cancel) { _: DialogInterface?, _: Int -> result.cancel() }
            setCancelable(false)

            if (!activity.isFinishing) show()
        }.create()
        return true
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateWindow(
        view: WebView,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message
    ): Boolean {
        val dialog = Dialog(view.context, R.style.Dialog)
        val newWebView = WebView(view.context).apply {
            with(settings) {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
            }
            webViewClient = WebViewClient(webViewListener)
            webChromeClient = object : WebChromeClient() {
                override fun onCloseWindow(window: WebView) {
                    dialog.dismiss()
                }
            }
        }

        dialog.setContentView(newWebView)
        dialog.show()
        dialog.setOnKeyListener { dialog1, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (newWebView.canGoBack()) {
                    newWebView.goBack()
                } else dialog1.dismiss()
                true
            } else false
        }
        val transport: WebView.WebViewTransport = resultMsg.obj as WebView.WebViewTransport
        transport.webView = newWebView
        resultMsg.sendToTarget()
        return true
    }
}
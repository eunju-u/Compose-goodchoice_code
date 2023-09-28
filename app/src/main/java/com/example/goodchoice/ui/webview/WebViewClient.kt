package com.example.goodchoice.ui.webview

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import java.net.URLDecoder

class WebViewClient(var webViewListener: WebViewListener? = null) : AccompanistWebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()
        val uri = Uri.parse(url)

        with(url) {
            when {
                // 웹뷰의 숙소 아이템 선택시
                startsWith("yeogi") -> {
                    val urlDecode = URLDecoder.decode(url, "utf-8")
                    val queryDecode = urlDecode.split("details?").toTypedArray()
                    val itemArr = queryDecode[1].split("&").toTypedArray()
                    val productUid = getParamsValue(itemArr, "productUid")
                    view?.context?.let {
                        //TODO eunju 아이템 상세 페이지로 이동
//                        val intent = Intent(it, ::class.java).apply {
//                        }
//                        view.context.startActivity(intent)
//                        if (it is WebViewActivity) {
//                            it.finish()
//                        }
                    }
                    return false
                }
                else -> return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    private fun getParamsValue(items: Array<String>, params: String): String? {
        var item: String? = null
        items.find { it.startsWith(params) }?.split("$params=")?.apply {
            if (size > 1) {
                item = this[1]
            }
        }
        return item
    }
}
package com.example.ui.webview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.Const
import com.example.ui.theme.TestTheme

class WebViewActivity : ComponentActivity() {
    companion object {
        val TAG: String = WebViewActivity::class.java.simpleName
    }

    private val webViewVM: WebViewViewModel by viewModels()

    private var webViewTitle: String = ""
    private var currentWebViewUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewTitle = intent.getStringExtra(Const.WEBVIEW_TITLE) ?: ""
        currentWebViewUrl = intent.getStringExtra(Const.WEBVIEW_URL) ?: ""

        setContent {
            TestTheme {
                WebViewContent(
                    viewModel = webViewVM,
                    title = webViewTitle,
                    url = currentWebViewUrl,
                    webViewListener = onWebViewListener,
                    onFinish = { this.finish() })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 웹뷰 초기화
        webViewVM.webView?.let {
            it.apply {
                removeAllViews()
                clearHistory()
                clearCache(true)
                removeAllViews()
                destroy()
            }
        }
        webViewVM.webView = null
    }

    // 웹뷰에서 call 받을 경우 url 로드
    var onWebViewListener = object : WebViewListener {
        override fun onUrl(url: String, type: String?) {
            loadUrl(url)
        }
    }

    fun loadUrl(url: String?) {
        webViewVM.webView?.let {
            currentWebViewUrl = url ?: ""
            it.loadUrl(url ?: "")
        }
    }
}
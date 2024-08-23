package com.example.ui.webview

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    var webView: WebView? = null
}
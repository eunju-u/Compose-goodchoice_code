package com.example.goodchoice.ui.webview

interface WebViewListener {
    fun onUrl(url: String, type: String? = null)
}
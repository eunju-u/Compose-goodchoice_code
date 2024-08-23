package com.example.ui.webview

interface WebViewListener {
    fun onUrl(url: String, type: String? = null)
}
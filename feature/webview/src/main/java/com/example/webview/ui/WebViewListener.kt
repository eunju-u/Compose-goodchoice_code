package com.example.webview.ui

interface WebViewListener {
    fun onUrl(url: String, type: String? = null)
}
package com.example.ui.webview

import android.annotation.SuppressLint
import android.webkit.WebBackForwardList
import android.webkit.WebSettings
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.ui_common.R
import com.example.ui_theme.Theme
import com.example.ui_theme.dp20
import com.example.ui.webview.widget.TopWebViewBarWidget
import com.google.accompanist.web.*

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewContent(
    viewModel: WebViewViewModel,
    title: String = "",
    url: String = "",
    webViewListener: WebViewListener,
    onFinish: () -> Unit = {}
) {
    val context = LocalContext.current
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState = rememberWebViewState(url = url)

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colorScheme.white)
        ) {
            TopWebViewBarWidget(title = title, url = url, onBack = { onFinish() },
                rightContent = {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier
                                .size(dp20)
                                .clickable { },
                            painter = painterResource(id = R.drawable.ic_share),
                            tint = Theme.colorScheme.gray,
                            contentDescription = "공유"
                        )
                    }
                }
            )
            if (url.isNotEmpty()) {
                WebView(state = webViewState,
                    navigator = webViewNavigator,
                    client = WebViewClient(),
                    chromeClient = WebChromeClient(
                        activity = context as WebViewActivity,
                        webViewListener = webViewListener
                    ),
                    onCreated = {
                        it.apply {
                            this.settings.apply {
                                javaScriptEnabled = true
                                allowContentAccess = true
                                domStorageEnabled = true
                                useWideViewPort = true
                                loadWithOverviewMode = true
                                setSupportMultipleWindows(true)

                                cacheMode = WebSettings.LOAD_DEFAULT
                                setSupportZoom(true)
                                displayZoomControls = false
                                builtInZoomControls = true
                                layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                                databaseEnabled = true
                                javaScriptCanOpenWindowsAutomatically = true
                                allowFileAccess = true
                            }

                            loadUrl(url)
                        }
                    })

            }
        }

        BackHandler(enabled = true) {
            if (webViewNavigator.canGoBack) {
                val list: WebBackForwardList? = viewModel.webView?.copyBackForwardList()
                list?.let {
                    if (it.currentIndex - 1 > 1) {
                        webViewNavigator.navigateBack()
                    } else {
                        onFinish()
                    }
                }
            } else {
                onFinish()
            }
        }
    }
}
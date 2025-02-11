package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.ui_theme.*

@Composable
fun LoadingWidget() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
                .background(Theme.colorScheme.darkGray)
        )
        CircularProgressIndicator(
            color = Theme.colorScheme.red
        )
    }
}
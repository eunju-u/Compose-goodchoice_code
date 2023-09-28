package com.example.goodchoice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*

@Composable
fun TopAppBarWidget(
    height: Dp = dp50,
    title: String = "",
    isCloseButton: Boolean = false, // close 버튼인기 back 버튼인지
    titleStyle: TextStyle = MaterialTheme.typography.labelLarge,
    onFinish: () -> Unit,
    rightContent: @Composable @UiComposable (() -> Unit)? = null
) {
    var textStyle by remember { mutableStateOf(titleStyle) }

    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .background(color = Theme.colorScheme.white)
    ) {
        Box(
            Modifier
                .padding(start = dp10)
                .align(Alignment.TopStart)
        ) {
            IconButton(onClick = onFinish) {
                Icon(
                    modifier = Modifier.size(dp20),
                    painter =
                    if (isCloseButton) painterResource(id = R.drawable.ic_close)
                    else painterResource(id = R.drawable.ic_back),
                    tint = Theme.colorScheme.gray,
                    contentDescription = null
                )
            }
        }
        Box(
            Modifier
                .padding(horizontal = dp10)
                .align(Alignment.Center)
//                .graphicsLayer(alpha = titleAlpha)
        ) {
            Text(
                text = title,
                style = textStyle,
                maxLines = 1,
                textAlign = TextAlign.Center,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight) {
                        textStyle =
                            textStyle.copy(fontSize = textStyle.fontSize * 0.95)
                    }
                })
        }
        Box(
            Modifier
                .padding(end = dp10)
                .align(Alignment.TopEnd)
        ) {
            if (rightContent != null) {
                rightContent()
            }
        }
    }
}

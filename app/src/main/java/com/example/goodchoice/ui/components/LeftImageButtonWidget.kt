package com.example.goodchoice.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.Theme

@Composable
fun LeftImageButtonWidget(
    modifier: Modifier = Modifier,
    title: Any? = null,
    hasOutline: Boolean = false,
    hasEndPadding: Boolean = true,
    endPadding: Dp = 5.dp,
    shape: Dp = 10.dp,
    borderWidth: Dp = 1.5.dp,
    enabled: Boolean = true,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Theme.colorScheme.blue,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    onItemClick: () -> Unit,
    content: @Composable @UiComposable (() -> Unit)? = null,
) {
    title?.let {
        ImageButtonWidget(
            modifier = Modifier.then(modifier),
            enabled = enabled,
            hasOutline = hasOutline,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            borderColor = borderColor,
            borderWidth = borderWidth,
            onItemClick = { onItemClick() }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (content != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else 0.dp),
                        contentAlignment = Alignment.Center
                    ) { content() }
                }

                if (it is String) {
                    TextWidget(
                        modifier = Modifier
                            .weight(1f, fill = false),
                        text = it,
                        style = style
                    )
                } else {
                    TextWidget(
                        modifier = Modifier
                            .weight(1f, fill = false),
                        text = it as AnnotatedString,
                        style = style
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewLeftImageButtonWidget() {
    LeftImageButtonWidget(title = "text", onItemClick = {}, content = { Text(text = "left_image") })
}
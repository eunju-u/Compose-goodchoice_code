package com.example.goodchoice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.Theme

@Composable
fun RightImageButtonWidget(
    modifier: Modifier = Modifier,
    title: Any? = null,
    hasOutline: Boolean = false,
    hasEndPadding: Boolean = true,
    endPadding: Dp = 10.dp,
    shape: Dp = 10.dp,
    borderWidth: Dp = 1.5.dp,
    enabled: Boolean = true,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    imageColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Theme.colorScheme.blue,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    onItemClick: () -> Unit,
    content: @Composable @UiComposable (() -> Unit)? = null,
) {
    title?.let {
        ButtonWidget(
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
                if (it is String) {
                    TextWidget(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else 0.dp)
                            .weight(1f, fill = false),
                        text = it,
                        color = contentColor,
                        style = style
                    )
                } else {
                    TextWidget(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else 0.dp)
                            .weight(1f, fill = false),
                        text = it as AnnotatedString,
                        color = contentColor,
                        style = style
                    )
                }
                if (content != null) {
                    Box(contentAlignment = Alignment.Center) { content() }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_right),
                        colorFilter = ColorFilter.tint(imageColor),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewRightImageButtonWidget() {
    RightImageButtonWidget(
        title = "text",
        onItemClick = {},
        content = { Text(text = "right_image") })
}
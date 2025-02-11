package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.ui.R
import com.example.ui_theme.Theme
import com.example.ui_theme.dp15

@Composable
fun SpaceBetweenRowWidget(
    modifier: Modifier = Modifier,
    isPadding: Boolean = false,
    padding: Dp = dp15,
    text: Any? = null,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    textColor: Color = Theme.colorScheme.darkGray,
    textClick: (() -> Unit)? = null,
    content: @Composable @UiComposable () -> Unit
) {
    val textModifier = if (textClick != null) {
        Modifier.clickable { textClick() }
    } else {
        Modifier
    }
    val rowModifier = if (isPadding) {
        modifier.padding(start = padding, end = padding)
    } else {
        modifier
    }

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        text?.let {
            if (it is String) {
                TextWidget(
                    modifier = Modifier
                        .fillMaxHeight()
                        .then(textModifier)
                        .wrapContentSize(Alignment.Center),
                    text = it,
                    style = textStyle,
                    color = textColor
                )
            } else {
                TextWidget(
                    modifier = Modifier
                        .fillMaxHeight()
                        .then(textModifier)
                        .wrapContentSize(Alignment.Center),
                    text = it as AnnotatedString,
                    style = textStyle,
                    color = textColor
                )
            }

        }
        content()
    }
}

@Preview
@Composable
fun PreviewSpaceBetweenRowWidget() {
    SpaceBetweenRowWidget(modifier = Modifier.fillMaxWidth(), text = "sdfsdfsd", content = {
        RightImageButtonWidget(
            title = "쿠폰 받기",
            contentColor = Theme.colorScheme.white,
            content = {
                Image(
                    painter = painterResource(id = R.drawable.ic_download),
                    colorFilter = ColorFilter.tint(color = Theme.colorScheme.white),
                    contentDescription = "쿠폰"
                )
            })
    })
}
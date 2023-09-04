package com.example.goodchoice.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp15

@Composable
fun SpaceBetweenRowWidget(
    modifier: Modifier = Modifier,
    isPadding: Boolean = false,
    text: String = "",
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
        modifier.padding(start = dp15, end = dp15)
    } else {
        modifier
    }
    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .then(textModifier)
                .wrapContentSize(Alignment.Center),
            text = text,
            style = textStyle,
            color = textColor
        )
        content()
    }
}
package com.example.goodchoice.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp0

@Composable
fun ButtonWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    hasOutline: Boolean = false,
    shape: Dp = 10.dp,
    borderWidth: Dp = 1.5.dp,
    enabled: Boolean = true,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Theme.colorScheme.blue,
    onItemClick: () -> Unit = {},
    content: @Composable @UiComposable () -> Unit = {},
) {
    TextButton(
        modifier = modifier
            .padding(outerPadding),
        shape = RoundedCornerShape(shape),
        colors = ButtonDefaults.textButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        border = if (hasOutline)
            BorderStroke(width = borderWidth, color = borderColor) else null,
        onClick = { onItemClick() }) {
        content()
    }
}
package com.example.goodchoice.ui.components

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.goodchoice.ui.theme.Theme

@Composable
fun TextWidget(
    text: String = "", style: TextStyle = MaterialTheme.typography.titleSmall,
    maxLines: Int = Int.MAX_VALUE, color: Color = Theme.colorScheme.darkGray
) {
    val textStyle by remember { mutableStateOf(style) }

    LaunchedEffect(true) {
        textStyle.copy(fontSize = textStyle.fontSize * 0.95)
    }

    Text(
        text = text,
        color = color,
        style = textStyle,
        textAlign = TextAlign.Center,
        maxLines = maxLines
    )
}
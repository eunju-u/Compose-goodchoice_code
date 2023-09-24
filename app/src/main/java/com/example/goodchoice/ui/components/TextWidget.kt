package com.example.goodchoice.ui.components

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.fontLargeSizeRatio
import com.example.goodchoice.ui.theme.fontSmallSizeRaio

@Composable
fun TextWidget(
    modifier: Modifier = Modifier,
    text: Any,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    color: Color = Theme.colorScheme.darkGray,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Start,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    var textStyle by remember { mutableStateOf(style) }

    val textFontSize = style.fontSize
    LaunchedEffect(key1 = screenWidth) {
        if (isPortrait) {
            textStyle = if (screenWidth < 400.dp) {
                if (textFontSize < 14.sp) {
                    textStyle.copy(fontSize = textStyle.fontSize * fontSmallSizeRaio)
                } else {
                    textStyle.copy(fontSize = textStyle.fontSize * fontLargeSizeRatio)
                }
            } else {
                style
            }
        }
    }

    if (text is String) {
        Text(
            modifier = modifier,
            text = text,
            color = color,
            style = textStyle,
            textAlign = textAlign,
            maxLines = maxLines,
            overflow = overflow
        )
    } else {
        Text(
            modifier = modifier,
            text = text as AnnotatedString,
            color = color,
            style = textStyle,
            textAlign = textAlign,
            maxLines = maxLines,
            overflow = overflow
        )
    }
}

@Preview
@Composable
fun previewTextWidget() {
    TextWidget(text = "TextWidget")
}
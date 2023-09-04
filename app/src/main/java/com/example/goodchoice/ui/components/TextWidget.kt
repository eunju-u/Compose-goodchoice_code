package com.example.goodchoice.ui.components

import android.content.res.Configuration
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.Theme

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
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    var textStyle by remember { mutableStateOf(style) }

    LaunchedEffect(key1 = screenWidth) {
        if (isPortrait) {
            if (screenWidth < 400.dp) {
                textStyle.copy(fontSize = textStyle.fontSize * 0.95)
            } else {
                textStyle = style
            }
        }
    }

    if (text is String) {
        Text(
            modifier = modifier,
            text = text,
            color = color,
            style = textStyle,
            textAlign = TextAlign.Center,
            maxLines = maxLines,
            overflow = overflow
        )
    } else {
        Text(
            modifier = modifier,
            text = text as AnnotatedString,
            color = color,
            style = textStyle,
            textAlign = TextAlign.Center,
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
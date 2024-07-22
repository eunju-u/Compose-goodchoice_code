package com.example.goodchoice.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.*

@Composable
fun LeftImageButtonWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    innerPadding: PaddingValues = PaddingValues(horizontal = dp12, vertical = dp10),
    title: Any? = null,
    hasEndPadding: Boolean = true,
    endPadding: Dp = dp5,
    shape: Dp = dp10,
    borderWidth: Dp = 1.5.dp,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Color.Transparent,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    isCenterHorizontalArrangement: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onItemClick: () -> Unit = {},
    content: @Composable @UiComposable (() -> Unit)? = null,
) {
    title?.let {
        CardWidget(
            modifier = modifier,
            outerPadding = outerPadding,
            cornerShape = RoundedCornerShape(shape),
            containerColor = containerColor,
            borderColor = borderColor,
            borderWidth = borderWidth,
            innerPadding = innerPadding,
            alignment = if (isCenterHorizontalArrangement) Alignment.Center else Alignment.TopStart,
            onItemClick = { onItemClick() }) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isCenterHorizontalArrangement) Arrangement.Center else Arrangement.Start
            ) {
                if (content != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else dp0),
                        contentAlignment = Alignment.Center
                    ) { content() }
                }

                if (it is String) {
                    TextWidget(
                        modifier = Modifier
                            .weight(1f, fill = false),
                        text = it,
                        style = style,
                        color = contentColor,
                        textAlign = TextAlign.Center,
                        maxLines = maxLines,
                        overflow = overflow
                    )
                } else {
                    TextWidget(
                        modifier = Modifier
                            .weight(1f, fill = false),
                        text = it as AnnotatedString,
                        style = style,
                        color = contentColor,
                        textAlign = TextAlign.Center,
                        maxLines = maxLines,
                        overflow = overflow
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLeftImageButtonWidget() {
    LeftImageButtonWidget(
        modifier = Modifier.fillMaxWidth(),
        title = "text",
        onItemClick = {}) { Text(text = "d") }
}
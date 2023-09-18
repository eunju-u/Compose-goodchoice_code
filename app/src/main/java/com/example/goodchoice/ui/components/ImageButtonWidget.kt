package com.example.goodchoice.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp0
import com.example.goodchoice.ui.theme.dp10
import com.example.goodchoice.ui.theme.dp12

@Composable
fun ImageButtonWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    innerPadding: PaddingValues = PaddingValues(horizontal = dp12, vertical = dp10),
    isLeftImage: Boolean = true,
    title: Any? = null,
    hasEndPadding: Boolean = true,
    endPadding: Dp = 5.dp,
    shape: Dp = 10.dp,
    borderWidth: Dp = 1.5.dp,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Color.Transparent,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    isCenterHorizontalArrangement: Boolean = true,
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (isLeftImage && content != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else 0.dp),
                        contentAlignment = Alignment.Center
                    ) { content() }
                }

                if (it is String) {
                    Log.d("eunju", "itit :$it")
                    TextWidget(
                        text = it,
                        style = style,
                        color = contentColor,
                        textAlign = TextAlign.Center
                    )
                } else {
                    TextWidget(
                        text = it as AnnotatedString,
                        style = style,
                        color = contentColor,
                        textAlign = TextAlign.Center
                    )
                }
                if (!isLeftImage && content != null) {
                    Box(
                        modifier = Modifier
                            .padding(start = if (hasEndPadding) endPadding else 0.dp),
                        contentAlignment = Alignment.Center
                    ) { content() }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewImageButtonWidget() {
    LeftImageButtonWidget(
        modifier = Modifier.fillMaxWidth(),
        title = "text",
        onItemClick = {}) { Text(text = "d") }
}
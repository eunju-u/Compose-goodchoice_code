package com.example.goodchoice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*

@Composable
fun RightImageButtonWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    innerPadding: PaddingValues = PaddingValues(horizontal = dp10, vertical = dp12),
    title: Any? = null,
    hasEndPadding: Boolean = true,
    endPadding: Dp = dp10,
    shape: Dp = dp10,
    borderWidth: Dp = 1.5.dp,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    imageColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Color.Transparent,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    isCenterHorizontalArrangement: Boolean = true,
    imageSize: Dp = dp30,
    onItemClick: () -> Unit = {},
    content: @Composable @UiComposable (() -> Unit)? = null,
) {
    title?.let {
        CardWidget(
            modifier = modifier.padding(outerPadding),
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
                if (it is String) {
                    TextWidget(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else dp0)
                            .weight(1f, fill = false),
                        text = it,
                        color = contentColor,
                        style = style,
                        textAlign = TextAlign.Center
                    )
                } else {
                    TextWidget(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else dp0)
                            .weight(1f, fill = false),
                        text = it as AnnotatedString,
                        color = contentColor,
                        style = style,
                        textAlign = TextAlign.Center
                    )
                }
                if (content != null) {
                    Box(contentAlignment = Alignment.Center) { content() }
                } else {
                    Image(
                        modifier = Modifier.size(imageSize),
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
fun PreviewRightImageButtonWidget() {
    RightImageButtonWidget(
        title = "text",
        onItemClick = {},
        content = { Text(text = "right_image") })
}
package com.example.goodchoice.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.example.goodchoice.ui.theme.dp5

@Composable
fun RoundImageWidget(
    modifier: Modifier = Modifier,
    roundShape: Dp = dp5,
    painter: Painter,
    contentScale: ContentScale = ContentScale.Crop,
    content: @Composable @UiComposable (() -> Unit)? = null
) {
    Box {
        Image(
            modifier = modifier
                .clip(RoundedCornerShape(roundShape)),
            painter = painter,
            contentScale = contentScale,
            contentDescription = "image"
        )

        if (content != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                content()
            }
        }
    }
}
package com.example.goodchoice.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*

@Composable
fun RoundImageWidget(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    roundShape: Dp = dp0,
    painter: Painter, //이미지
    boxAlignment: Alignment = Alignment.BottomStart, //상위에 올라가는 뷰의 정렬
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = "image",
    content: @Composable @UiComposable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(roundShape)),
    ) {
        Image(
            modifier = imageModifier,
            painter = painter,
            contentScale = contentScale,
            contentDescription = contentDescription
        )

        if (content != null) {
            Box(
                modifier = Modifier
                    .align(boxAlignment)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun PreviewRoundImageWidget() {
    RoundImageWidget(
        modifier = Modifier
            .size(dp55),
        roundShape = dp30,
        painter = painterResource(id = R.drawable.bg_white),
        boxAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.str_close),
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
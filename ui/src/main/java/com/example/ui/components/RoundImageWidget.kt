package com.example.ui.components

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.ui.R
import com.example.ui_theme.*

@Composable
fun RoundImageWidget(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    roundShape: Dp = dp0,
    painter: Painter, //이미지
    boxAlignment: Alignment = Alignment.BottomStart, //상위에 올라가는 뷰의 정렬
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = "image",
    isVisibleShadow: Boolean = false, //쉐도우 적용 여부
    imageColor: ColorFilter? = null,
    content: @Composable @UiComposable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = if (isVisibleShadow) dp15 else dp0,
                shape = RoundedCornerShape(roundShape),
                clip = true
            ),
    ) {
        Image(
            modifier = imageModifier,
            painter = painter,
            contentScale = contentScale,
            contentDescription = contentDescription,
            colorFilter = imageColor
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
            text = "닫기",
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
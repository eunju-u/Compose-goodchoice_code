package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.ui.R
import com.example.ui_theme.*

@Composable
fun CategoryItemWidget(
    painter: Painter? = null,
    name: String = "",
    width: Dp = dp0,
    height: Dp = dp0,
    bottomPadding: Dp = dp5,
    imageSize: Dp = dp35,
    imageClip: Dp = dp0,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    textColor: Color = Theme.colorScheme.darkGray,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    onItemClick: (() -> Unit)? = null
) {
    val widthModifier = if (width > dp0) {
        Modifier.width(width)
    } else {
        Modifier
    }

    val heightModifier = if (height > dp0) {
        Modifier.height(height)
    } else {
        Modifier
    }

    val clickModifier = if (onItemClick != null) {
        Modifier.clickable { onItemClick() }
    } else {
        Modifier
    }
    Column(
        modifier = Modifier
            .then(widthModifier)
            .then(heightModifier)
            .then(clickModifier),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(imageSize)
                .clip(RoundedCornerShape(imageClip)),
            painter = painter ?: painterResource(id = R.drawable.bg_white),
            contentDescription = name,
            colorFilter = colorFilter,
            contentScale = contentScale,
        )

        TextWidget(
            modifier = Modifier.padding(top = bottomPadding, bottom = dp2),
            text = name,
            style = textStyle,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}

@Preview
@Composable
fun PreviewCategoryItemWidget() {
    CategoryItemWidget(name = "공간대여", painter = painterResource(id = R.drawable.ic_airplane))
}
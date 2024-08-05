package com.example.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.common.theme.Theme
import com.example.common.theme.*

enum class ShapeType {
    RECTANGLE, CIRCLE
}

@Composable
fun ShapeButton(
    size: Dp = dp15,
    isChecked: Boolean = false,
    normalColor: Color = Theme.colorScheme.white,
    checkedColor: Color = Theme.colorScheme.white,
    borderColor: Color = if (isChecked) Color.Transparent else Theme.colorScheme.pureGray,
    shape: ShapeType = ShapeType.CIRCLE,
    content: @Composable @UiComposable (() -> Unit)? = null,
    onItemClick: (() -> Unit)? = null
) {
    val radius = RoundedCornerShape(if (shape == ShapeType.CIRCLE) dp50 else dp0)
    Box(
        modifier = Modifier
            .size(size)
            .background(
                shape = radius,
                color = if (isChecked) checkedColor else normalColor
            )
            .border(BorderStroke(width = dp1, color = borderColor), shape = radius)
            .clip(shape = radius)
            .then(if (onItemClick != null) Modifier.clickable { onItemClick() } else Modifier)
    ) {
        if (content != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                content()
            }
        }
    }
}
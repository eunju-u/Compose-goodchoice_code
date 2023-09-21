package com.example.goodchoice.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.goodchoice.ui.theme.*

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
    shape: ShapeType = ShapeType.CIRCLE
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
    )
}
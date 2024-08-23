package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Theme
import com.example.ui.theme.*

@Composable
fun TagWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    title: String = "",
    contentColor: Color = Theme.colorScheme.pureGray,
    containerColor: Color = Theme.colorScheme.white,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    borderColor: Color = Color.Transparent,
    shape: Dp = dp0,
    onItemClick: (() -> Unit)? = null,
    innerPadding: PaddingValues = PaddingValues(horizontal = dp10, vertical = dp12),
) {

    Text(modifier = modifier
        .padding(outerPadding)
        .then(if (onItemClick != null) {
            Modifier.clickable { onItemClick() }
        } else {
            Modifier
        }.background(color = containerColor, shape = RoundedCornerShape(shape)))
        .border(shape = RoundedCornerShape(shape), width = dp1, color = borderColor)
        .padding(innerPadding),
        color = contentColor,
        text = title, textAlign = TextAlign.Center,
        style = style
    )
}

@Preview
@Composable
fun PreviewTagWidget() {
    TagWidget(
        title = "혜택",
        containerColor = Theme.colorScheme.lightPink,
        contentColor = Theme.colorScheme.red,
        shape = dp5,
    )
}
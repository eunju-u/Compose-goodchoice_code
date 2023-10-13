package com.example.goodchoice.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp0
import com.example.goodchoice.ui.theme.dp15
import com.example.goodchoice.ui.theme.dp5

@Composable
fun RowTwoWidget(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(dp0),
    leftModifier: Modifier = Modifier,
    leftText: String = "",
    rightText: Any? = null,
    leftStyle: TextStyle = MaterialTheme.typography.labelSmall,
    rightStyle: TextStyle = MaterialTheme.typography.labelSmall,
    leftColor: Color = Theme.colorScheme.darkGray,
    rightColor: Color = Theme.colorScheme.darkGray,
    endPadding: Dp = dp5,
    isCenter: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onItemClick: (() -> Unit)? = null
) {
    Row(
        modifier
            .then(if (onItemClick != null) Modifier.clickable { onItemClick() } else Modifier)
            .padding(innerPadding),
        verticalAlignment = if (isCenter) Alignment.CenterVertically else Alignment.Top) {
        Text(
            modifier = Modifier
                .padding(end = endPadding)
                .then(leftModifier),
            text = leftText, style = leftStyle, color = leftColor
        )
        rightText?.let {
            if (it is String) {
                Text(
                    text = it,
                    style = rightStyle,
                    color = rightColor,
                    maxLines = maxLines,
                    overflow = overflow
                )
            } else {
                Text(
                    text = it as AnnotatedString,
                    style = rightStyle,
                    color = rightColor,
                    maxLines = maxLines,
                    overflow = overflow
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewInfoWidget() {
    RowTwoWidget(
        leftText = "객실정보",
        rightText = "기준4인 * 최대4인",
        leftColor = Theme.colorScheme.gray,
        rightColor = Theme.colorScheme.darkGray,
        endPadding = dp15
    )
}
package com.example.goodchoice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.theme.Theme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.dp5

@Composable
fun TagWidget(
    modifier: Modifier = Modifier,
    title: String = "",
    contentColor: Color = Theme.colorScheme.pureGray,
    containerColor: Color = Theme.colorScheme.white,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    shape: Dp = 3.dp,
    onClick: (() -> Unit)? = null,
) {

    Text(modifier = modifier
        .then(if (onClick != null) {
            Modifier.clickable { onClick() }
        } else {
            Modifier
        }.background(color = containerColor, shape = RoundedCornerShape(shape)))
        .padding(dp5),
        color = contentColor,
        text = title, textAlign = TextAlign.Center,
        style = style
    )
}

@Preview
@Composable
fun PreviewTagWidget(){
    TagWidget(
        title = stringResource(id = R.string.str_benefit),
        containerColor = Theme.colorScheme.lightPink,
        contentColor = Theme.colorScheme.red,
        shape = dp5,
    )
}
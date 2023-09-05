package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*

@Composable
fun RoundCloseWidget() {
    RoundImageWidget(
        modifier = Modifier.size(dp40),
        painter = painterResource(id = R.drawable.bg_white),
        roundShape = dp30
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(dp20),
                painter = painterResource(id = R.drawable.ic_close),
                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                contentDescription = "image"
            )
            Text(
                text = stringResource(id = R.string.str_close),
                color = Theme.colorScheme.darkGray,
                style = MaterialTheme.typography.labelSmall
            )

        }
    }
}

@Preview
@Composable
fun previewRoundCloseWidget() {
    RoundCloseWidget()
}
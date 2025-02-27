package com.example.home.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.ui_theme.*
import com.example.ui_common.R
import com.example.ui_common.components.RoundImageWidget

@Composable
fun RoundCloseWidget(modifier: Modifier = Modifier) {
    RoundImageWidget(
        imageModifier = Modifier
            .size(dp55)
            .then(modifier),
        painter = painterResource(id = R.drawable.bg_white),
        roundShape = dp30,
        boxAlignment = Alignment.Center,
        imageColor = ColorFilter.tint(Theme.colorScheme.white),
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(bottom = dp5)
                        .size(dp20),
                    painter = painterResource(id = R.drawable.ic_close),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                    contentDescription = "image"
                )
                Text(
                    text = stringResource(id = R.string.str_close),
                    color = Theme.colorScheme.darkGray,
                    style = MaterialTheme.typography.labelSmall
                )

            }
        }
    )
}

@Preview
@Composable
fun PreviewRoundCloseWidget() {
    RoundCloseWidget()
}
package com.example.goodchoice.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.*

@Composable
fun SplashContent() {
    val configuration = LocalConfiguration.current
    val fullWidth = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(Color.Blue, Color.LightGray)),
                alpha = 0.4f
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            Image(
                modifier = Modifier.size((fullWidth / 2).dp),
                painter = painterResource(id = R.drawable.img_splash_airplane),
                contentDescription = "null"
            )
        }

        Box(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Image(
                modifier = Modifier.size((fullWidth / 2).dp),
                painter = painterResource(id = R.drawable.img_splash_building),
                contentDescription = "null"
            )
        }

        Box(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Image(
                modifier = Modifier.size((fullWidth / 2).dp),
                painter = painterResource(id = R.drawable.img_splash_travel),
                contentDescription = "null"
            )
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier.size(dp200),
                painter = painterResource(id = R.drawable.img_goodchoice),
                contentDescription = stringResource(id = R.string.str_app_name)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplashContent() {
    SplashContent()
}
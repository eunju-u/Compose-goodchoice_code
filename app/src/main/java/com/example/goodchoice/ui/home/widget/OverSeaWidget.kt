package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import  androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.data.OverSeaCityItem
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil

/**
 * 홈 > 해외 도시
 */
@Composable
fun OverSeaWidget(item: OverSeaCityItem) {
    val painter =
        rememberAsyncImagePainter(
            model = item.cityImage,
            error = painterResource(id = ConvertUtil.convertOverSeaImage(item.id))
        )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundImageWidget(
            modifier = Modifier
                .width(dp50)
                .height(dp50), painter = painter
        )
        TextWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp8),
            text = item.cityName ?: "",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun previewOverSeaWidget() {
    OverSeaWidget(OverSeaCityItem(id = 11000, cityName = "오사카"))
}
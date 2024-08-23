package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import  androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.domain.model.OverSeaCityItem
import com.example.ui.utils.ConvertUtil

/**
 * 홈 > 해외 도시
 */
@Composable
fun OverSeaWidget(item: OverSeaCityItem) {
    val painter =
        rememberAsyncImagePainter(
            model = item.cityImage,
            error = painterResource(id = ConvertUtil.convertOverSeaImage(item.code ?: ""))
        )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundImageWidget(
            imageModifier = Modifier
                .width(dp50)
                .height(dp50), painter = painter
        )
        TextWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp8),
            text = item.cityName ?: "",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewOverSeaWidget() {
    OverSeaWidget(OverSeaCityItem(cityName = "오사카"))
}
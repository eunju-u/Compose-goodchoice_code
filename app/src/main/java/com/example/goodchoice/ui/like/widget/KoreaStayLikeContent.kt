package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.KoreaDateWidget
import com.example.goodchoice.ui.theme.Theme

@Composable
fun KoreaStayLikeContent(
    koreaLikeData: List<StayItem>,
    clickLike: (stayId: String) -> Unit = {},
    onItemClick: () -> Unit = {},
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, content = {
            item {
                CardWidget(
                    containerColor = Theme.colorScheme.white,
                    content = { KoreaDateWidget(context) })
            }
            item {
                koreaLikeData.forEach { item ->
                    KoreaStayItemWidget(item, { clickLike(item.id ?: "") }, onItemClick)
                }
            }
        })
}
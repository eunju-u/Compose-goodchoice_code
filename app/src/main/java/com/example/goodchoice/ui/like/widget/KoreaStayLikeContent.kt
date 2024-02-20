package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.KoreaDateWidget

@Composable
fun KoreaStayLikeContent() {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, content = {
            item {
//                CardWidget(content = { KoreaDateWidget() })
            }
        })
}
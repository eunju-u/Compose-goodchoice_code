package com.example.goodchoice.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goodchoice.data.StayData

@Composable
fun HotelVerticalWidget(
    modifier: Modifier,
    stayData: StayData
) {
    val scrollState = rememberLazyListState()
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
            text = stayData.title ?: ""
        )
        if (!stayData.stayList.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                state = scrollState,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(items = stayData.stayList) { index, item ->
                    HotelItemWidget(stayItem = item)
                }
            }
        }
    }
}
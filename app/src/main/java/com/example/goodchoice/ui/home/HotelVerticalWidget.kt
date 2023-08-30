package com.example.goodchoice.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodchoice.Const
import com.example.goodchoice.data.StayData
import com.example.goodchoice.ui.theme.GMarketSansFamily
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.RightImageButtonWidget

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
            modifier = Modifier.padding(15.dp),
            text = stayData.title ?: ""
        )

        //horizontal item
        if (!stayData.stayList.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = scrollState,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(items = stayData.stayList) { index, item ->
                    if (index == 0) Spacer(Modifier.width(15.dp))
                    HotelItemWidget(stayItem = item)
                    if (index == stayData.stayList.lastIndex) Spacer(Modifier.width(15.dp))
                }
            }
        }

        if (stayData.isMore) {
            //더보기 버튼
            val text: Any =
                when (stayData.type) {
                    Const.TODAY_HOTEL -> {
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontFamily = GMarketSansFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = Theme.colorScheme.blue
                                )
                            ) {
                                val str = stringResource(id = R.string.str_today_hotel_more)
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(str.substring(0, 5))
                                }
                                append(str.substring(5))
                            }
                        }
                    }
                    Const.HOT_HOTEL -> {
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontFamily = GMarketSansFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = Theme.colorScheme.blue
                                )
                            ) {
                                val str = stringResource(id = R.string.str_hot_hotel_more)
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(str.substring(0, 2))
                                }
                                append(str.substring(2))
                            }
                        }
                    }
                    else -> {
                        stringResource(id = R.string.str_more)
                    }
                }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RightImageButtonWidget(
                    title = text,
                    hasOutline = true,
                    borderWidth = 1.dp,
                    imageColor = Theme.colorScheme.blue,
                    shape = 20.dp,
                    onItemClick = {})
            }
        }
    }
}
package com.example.goodchoice.ui.home.widget

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.Const
import com.example.goodchoice.api.data.StayData
import com.example.goodchoice.R
import com.example.goodchoice.api.data.StayItem
import com.example.goodchoice.ui.components.RightImageButtonWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.home.homeData.HomeRecentData
import com.example.goodchoice.ui.recentSeen.RecentSeenActivity
import com.example.goodchoice.ui.theme.*

@Composable
fun HotelVerticalWidget(
    modifier: Modifier = Modifier,
    stayData: StayData = StayData(),
    recentStay: MutableState<HomeRecentData> = mutableStateOf(HomeRecentData())
) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val textStyle = MaterialTheme.typography.labelLarge

    val stayList = stayData.stayList ?: emptyList()
    Column(
        modifier = modifier
    ) {
        TextWidget(
            modifier = Modifier.padding(
                start = dp15,
                bottom = dp10,
                end = dp15,
                top = dp20
            ),
            text = stayData.title ?: "",
            style = textStyle
        )

        //horizontal item
        if (stayList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = scrollState,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(items = stayList) { index, item ->
                    if (index == 0) Spacer(Modifier.width(15.dp))
                    stayData.type?.let { type ->
                        if (type == Const.TODAY_HOTEL || type == Const.HOT_HOTEL) {
                            HotelItemWidget(
                                stayItem = item,
                                recentStay = recentStay
                            )
                        } else if (type == Const.RECENT_HOTEL) {
                            RecentSeenWidget(stayItem = item)
                        }
                    } ?: HotelItemWidget(
                        stayItem = item,
                        recentStay = recentStay
                    )

                    if (index == stayList.lastIndex) Spacer(Modifier.width(15.dp))
                }
            }
        }

        if (stayList.size > 3) {
            //더보기 버튼
            val text: Any =
                when (stayData.type) {
                    Const.TODAY_HOTEL -> {
                        buildAnnotatedString {
                            val str = stringResource(id = R.string.str_today_hotel_more)
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(str.substring(0, 5))
                            }
                            append(str.substring(5))
                        }
                    }
                    Const.HOT_HOTEL -> {
                        buildAnnotatedString {
                            val str = stringResource(id = R.string.str_hot_hotel_more)
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(str.substring(0, 2))
                            }
                            append(str.substring(2))
                        }
                    }
                    else -> {
                        stringResource(id = R.string.str_more)
                    }
                }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dp10, bottom = dp10),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RightImageButtonWidget(
                    title = text,
                    hasOutline = true,
                    borderWidth = 1.dp,
                    imageColor = Theme.colorScheme.blue,
                    shape = dp20,
                    style = MaterialTheme.typography.labelSmall,
                    contentColor = Theme.colorScheme.blue,
                    onItemClick = {
                        if (stayData.type == Const.RECENT_HOTEL) {
                            context.startActivity(
                                Intent(
                                    context,
                                    RecentSeenActivity::class.java
                                )
                            )
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun previewHotelVerticalWidget() {
    HotelVerticalWidget(
        stayData = StayData(
            type = Const.TODAY_HOTEL,
            title = "오늘 체크인 호텔 특가",
            stayList = listOf(
                StayItem(
                    label = "호텔.리조트",
                    name = "[당일특가] 스탠포드 호출 서울",
                    star = "9.3",
                    commentCount = 1842,
                    location = "마포구.디지털미디어",
                    discountPer = 0,
                    defaultPrice = "129000",
                    discountPrice = "0",
                    level = "레지던스",
                ),
                StayItem(
                    label = "호텔.리조트",
                    name = "브릿지호텔 인천송도 (구 호텔 스카이파크 인천 송도)",
                    star = "9.1",
                    commentCount = 1118,
                    location = "연수구.인천대입구역",
                    discountPer = 60,
                    defaultPrice = "250000",
                    discountPrice = "다른 날짜 확인",
                    level = "아파트먼트",
                )
            )
        )
    )
}
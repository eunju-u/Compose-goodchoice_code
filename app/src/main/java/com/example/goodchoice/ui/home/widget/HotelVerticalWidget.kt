package com.example.goodchoice.ui.home.widget

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.Const
import com.example.ui.components.TextWidget
import com.example.ui.R
import com.example.ui.components.ImageButtonWidget
import com.example.ui.theme.*
import com.example.domain.model.OverseaSpecialItem
import com.example.domain.model.StayItem
import com.example.ui.recentSeen.RecentSeenActivity
import com.example.ui.utils.StringUtil

@Composable
fun HotelVerticalWidget(
    modifier: Modifier = Modifier,
    type: String = "",
    title: String = "",
    stayList: List<Any> = listOf(),
) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val textStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)

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
            text = StringUtil.setTextColor(
                originText = title,
                targetText = if (type == Const.TODAY_HOTEL) "호텔 특가" else if (type == Const.HOT_HOTEL) "이번 주 HOT" else if (type == Const.OVERSEA_SPECIAL) "해외 항공 + 숙소" else ""
            ),
            style = textStyle,
            textAlign = TextAlign.Center
        )

        //horizontal item
        if (stayList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = scrollState,
                horizontalArrangement = Arrangement.spacedBy(dp10)
            ) {
                itemsIndexed(items = stayList) { index, item ->
                    if (index == 0) Spacer(Modifier.width(dp15))
                    if (item is StayItem) {
                        if (type == Const.TODAY_HOTEL || type == Const.HOT_HOTEL) {
                            KoreaStayItemWidget(
                                stayDataType = type,
                                stayItem = item
                            )
                        } else if (type == Const.RECENT_HOTEL) {
                            RecentSeenWidget(stayItem = item)
                        }
                    } else if (item is OverseaSpecialItem) {
                        OverseaStayItemWidget(item)
                    }
                    if (index == stayList.lastIndex) Spacer(Modifier.width(dp15))
                }
            }
        }

        if (stayList.size > 3) {
            //더보기 버튼
            val text: Any =
                when (type) {
                    Const.TODAY_HOTEL -> {
                        StringUtil.setTextBold(
                            originText = stringResource(id = R.string.str_today_hotel_more),
                            targetText = "호텔 특가"
                        )
                    }
                    Const.HOT_HOTEL -> {
                        StringUtil.setTextBold(
                            originText = stringResource(id = R.string.str_hot_hotel_more),
                            targetText = "인기"
                        )
                    }
                    Const.OVERSEA_SPECIAL -> {
                        StringUtil.setTextBold(
                            originText = stringResource(id = R.string.str_oversea_airplane_stay_more),
                            targetText = "항공 + 숙소 특가"
                        )
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
                ImageButtonWidget(
                    title = text,
                    borderWidth = dp1,
                    isLeftImage = false,
                    shape = dp30,
                    style = MaterialTheme.typography.labelSmall,
                    borderColor = Theme.colorScheme.blue,
                    contentColor = Theme.colorScheme.blue,
                    innerPadding = PaddingValues(horizontal = dp10, vertical = dp8),
                    onItemClick = {
                        if (type == Const.RECENT_HOTEL) {
                            context.startActivity(
                                Intent(
                                    context,
                                    RecentSeenActivity::class.java
                                )
                            )
                        }
                    }, content = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_right),
                            colorFilter = ColorFilter.tint(Theme.colorScheme.blue),
                            contentDescription = null
                        )
                    })
            }
        }
    }
}

@Preview
@Composable
fun PreviewHotelVerticalWidget() {
    HotelVerticalWidget(
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
}
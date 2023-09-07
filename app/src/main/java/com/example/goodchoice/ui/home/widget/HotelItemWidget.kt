package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.api.data.StayItem
import com.example.goodchoice.R
import com.example.goodchoice.ui.home.homeData.HomeRecentData
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil

/**
 * 숙소 item 뷰
 */
@Composable
fun HotelItemWidget(
    stayItem: StayItem = StayItem(),
    recentStay: MutableState<HomeRecentData> = mutableStateOf(HomeRecentData())
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(IntrinsicSize.Min)
            .border(
                width = 1.dp, color = Theme.colorScheme.pureGray, shape = RoundedCornerShape(dp10)
            )
            .clip(RoundedCornerShape(dp10))
            .clickable {
                recentStay.value.stayList?.let {
                    if (it.contains(stayItem)) {
                        it.remove(stayItem)
                    }
                    it.add(0, stayItem)

                }
            }, verticalArrangement = Arrangement.Center
    ) {
        val padding = PaddingValues(start = dp20, end = dp20)
        val label = stayItem.label ?: ""
        val name = stayItem.name ?: ""
        val discountPer = stayItem.discountPer ?: 0
        val defaultPrice = stayItem.defaultPrice ?: ""
        val discountPrice = stayItem.discountPrice ?: "" //다른 날짜 확인 of 120000
        val isDefaultPriceNumber = defaultPrice.toIntOrNull() != null // 숫자로 변경 가능
        val isDiscountPriceNumber = discountPrice.toIntOrNull() != null // 숫자로 변경 가능

        val convertDefaultPrice =
            if (isDefaultPriceNumber) StringUtil.convertCommaString(defaultPrice) else defaultPrice
        val convertDiscountPrice =
            if (isDiscountPriceNumber) StringUtil.convertCommaString(discountPrice) else discountPrice

        val painter =
            if (stayItem.imageList?.isNotEmpty() == true)
                rememberAsyncImagePainter(
                    model = stayItem.imageList[0],
                    painterResource(id = R.drawable.bg_yellow)
                )
            else painterResource(id = R.drawable.bg_yellow)

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            painter = painter,
            contentDescription = "호텔 사진"
        )


        Spacer(modifier = Modifier.height(dp5))
        if (label.isNotEmpty()) {
            Text(modifier = Modifier.padding(padding), text = label)
            Spacer(modifier = Modifier.height(dp5))
        }

        if (name.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(padding),
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(dp5))
        }

        Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(dp15),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "별점"
            )
            Text(text = "${stayItem.star ?: 0}")
            Text(text = "(${stayItem.commentCount ?: 0})")
            Spacer(modifier = Modifier.width(dp10))
            Text(
                text = stayItem.location ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Theme.colorScheme.gray
            )
        }
        Spacer(modifier = Modifier.height(dp5))

        Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
            if (discountPer > 0) {
                Text(text = "${discountPer}%", color = Theme.colorScheme.red)
                Spacer(modifier = Modifier.width(dp10))
            }
            if (isDiscountPriceNumber) {
                if (discountPrice != "") {
                    Text(
                        text = "$convertDefaultPrice ",
                        color = Theme.colorScheme.gray,
                        textDecoration = TextDecoration.LineThrough //취소선
                    )
                } else {
                    Text(text = "$convertDefaultPrice ")
                }
            }

        }
        Text(modifier = Modifier.padding(padding), text = convertDiscountPrice)
        Spacer(modifier = Modifier.height(dp15))
    }
}

@Preview
@Composable
fun previewHotelItemWidget() {
    HotelItemWidget(
        StayItem(
            label = "",
            name = "태안 팜비치펜션",
            star = "9.7",
            commentCount = 308,
            location = "청포대해변 앞",
            discountPer = 0,
            defaultPrice = "130000",
            discountPrice = "1250000",
            level = "아파트먼트",
        )
    )
}
package com.example.goodchoice.ui.home.widget

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.Const
import com.example.goodchoice.api.data.StayItem
import com.example.goodchoice.R
import com.example.goodchoice.ui.home.homeData.MutableRecentData
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil

/**
 * 홈의 국내 숙소 item 뷰
 */
@Composable
fun KoreaStayItemWidget(
    stayDataType: Int = Const.TODAY_HOTEL,
    stayItem: StayItem = StayItem(),
    recentStay: MutableState<MutableRecentData> = mutableStateOf(MutableRecentData())
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val width = 240.0
    var itemWidth by remember { mutableStateOf(width) }

    //해상도에 따라 호텔 item view 가로 조정
    LaunchedEffect(key1 = screenWidth) {
        itemWidth = if (screenWidth < 400.dp) {
            width * fontLargeSizeRatio
        } else {
            width
        }
    }

    Column(
        modifier = Modifier
            .width(itemWidth.dp)
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
            if (stayItem.mainImage?.isNotEmpty() == true)
                rememberAsyncImagePainter(
                    model = stayItem.mainImage,
                    painterResource(id = R.drawable.bg_white)
                )
            else painterResource(id = R.drawable.bg_white)

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(dp170),
            painter = painter,
            contentScale = ContentScale.FillWidth,
            contentDescription = "호텔 사진"
        )

        Spacer(modifier = Modifier.height(dp8))
        if (stayDataType != Const.HOT_HOTEL && label.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(padding),
                text = label,
                color = Theme.colorScheme.gray,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(dp8))
        }

        if (name.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(padding),
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(dp3))
        }

        if (!stayItem.star.isNullOrEmpty() || !stayItem.location.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!stayItem.star.isNullOrEmpty()) {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "별점"
                    )
                    Text(
                        text = stayItem.star,
                        color = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = " (${stayItem.commentCount ?: 0})",
                        color = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.width(dp10))
                }

                if (!stayItem.location.isNullOrEmpty()) {
                    Text(
                        text = stayItem.location,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(dp10))
        }

        if (stayDataType == Const.HOT_HOTEL) {
            Spacer(modifier = Modifier.height(dp20))
        }

        Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
            if (discountPer > 0) {
                Text(
                    text = "${discountPer}%",
                    color = Theme.colorScheme.red,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(dp10))
            }
            if (isDiscountPriceNumber) {
                if (discountPrice != "" && convertDefaultPrice != "") {
                    Text(
                        text = convertDefaultPrice,
                        color = Theme.colorScheme.gray,
                        textDecoration = TextDecoration.LineThrough, //취소선
                        style = MaterialTheme.typography.labelSmall
                    )
                } else {
                    Text(
                        text = convertDefaultPrice,
                        color = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(dp3))

        Text(
            modifier = Modifier.padding(padding),
            text = convertDiscountPrice,
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(dp15))
    }
}

@Preview
@Composable
fun PreviewHotelItemWidget() {
    KoreaStayItemWidget(
        stayItem = StayItem(
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
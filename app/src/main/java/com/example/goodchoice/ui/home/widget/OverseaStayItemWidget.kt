package com.example.goodchoice.ui.home.widget

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.api.data.OverseaSpecialItem
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.TextWidget

/**
 * 홈의 해외 숙소 item 뷰
 */
@Composable
fun OverseaStayItemWidget(
    stayItem: OverseaSpecialItem = OverseaSpecialItem(),
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val width = 260.0
    var itemWidth by remember { mutableStateOf(width) }

    //해상도에 따라 호텔 item view 가로 조정
    LaunchedEffect(key1 = screenWidth) {
        itemWidth = if (screenWidth < 400.dp) {
            width / 1.9
        } else {
            width
        }
    }

    Column(
        modifier = Modifier
            .width(itemWidth.dp)
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(dp10))
            .clickable {},
        verticalArrangement = Arrangement.Center
    ) {
        val padding = PaddingValues(start = dp2, end = dp2)
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

        RoundImageWidget(
            imageModifier = Modifier
                .fillMaxWidth()
                .height(dp170),
            roundShape = dp10,
            painter = painter,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.65f)
                        .background(color = Theme.colorScheme.darkGray)
                        .padding(start = dp8, end = dp8, top = dp5, bottom = dp5),

                    ) {
                    TextWidget(
                        text = stringResource(id = R.string.str_oversea_special_info),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Transparent
                    )
                }
                TextWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = dp8, end = dp8, top = dp5, bottom = dp5),
                    text = stringResource(id = R.string.str_oversea_special_info),
                    style = MaterialTheme.typography.labelSmall,
                    color = Theme.colorScheme.white,
                    textAlign = TextAlign.Center
                )
            }
        )

        Spacer(modifier = Modifier.height(dp8))

        if (name.isNotEmpty()) {
            TextWidget(
                modifier = Modifier.padding(padding),
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(lineHeight = 13.sp)
            )
            Spacer(modifier = Modifier.height(dp5))
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
        Spacer(modifier = Modifier.height(dp5))

        Text(
            modifier = Modifier.padding(padding),
            text = convertDiscountPrice,
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(dp15))
    }
}
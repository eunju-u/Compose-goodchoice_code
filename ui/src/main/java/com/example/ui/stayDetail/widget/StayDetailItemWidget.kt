package com.example.ui.stayDetail.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.components.*
import com.example.domain.model.RoomItem
import com.example.ui.utils.ConvertUtil
import com.example.ui.utils.StringUtil
import com.example.ui_theme.*

@Composable
fun StayDetailItemWidget(roomItem: RoomItem = RoomItem()) {
    val inTime = roomItem.inTime ?: ""
    val outTime = roomItem.outTime ?: ""
    val count = roomItem.count ?: 0
    val coupon = roomItem.coupon ?: ""
    val discountPer = roomItem.discountPer ?: 0
    val defaultPrice = roomItem.defaultPrice ?: ""
    val discountPrice = roomItem.discountPrice ?: "" //다른 날짜 확인 of 120000
    val isDefaultPriceNumber = defaultPrice.toIntOrNull() != null // 숫자로 변경 가능
    val isDiscountPriceNumber = discountPrice.toIntOrNull() != null // 숫자로 변경 가능
    val info = roomItem.info ?: ""
    val addInfo = roomItem.addInfo ?: ""

    val convertDefaultPrice =
        if (isDefaultPriceNumber) ConvertUtil.convertCommaString(defaultPrice) else defaultPrice
    val convertDiscountPrice =
        if (isDiscountPriceNumber) ConvertUtil.convertCommaString(discountPrice) else discountPrice

    CardWidget(
        isVisibleShadow = true,
        outerPadding = PaddingValues(dp10),
        innerPadding = PaddingValues(top = dp15, start = dp10, end = dp10, bottom = dp7),
        cornerShape = RoundedCornerShape(dp15),
        containerColor = Theme.colorScheme.white,
        shadowColor = Theme.colorScheme.gray
    ) {
        Column {
            Text(
                text = roomItem.name ?: "",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(dp20))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .width(dp120)
                        .height(dp150)
                        .background(
                            shape = RoundedCornerShape(dp10),
                            color = Theme.colorScheme.white
                        )
                        .clip(RoundedCornerShape(dp10)),
                    contentScale = ContentScale.FillHeight,
                    painter = rememberAsyncImagePainter(
                        model = roomItem.image,
                        painterResource(id = R.drawable.bg_white)
                    ),
                    contentDescription = "이미지",
                )

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (count > 0) {
                        Box(
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .padding(bottom = dp3)
                        ) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomEnd),
                                thickness = dp5,
                                color = Theme.colorScheme.yellow
                            )
                            Text(
                                text = stringResource(id = R.string.str_left_count, count),
                                color = Theme.colorScheme.darkGray,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                        text = StringUtil.setTextBold(
                            originText = "${convertDiscountPrice}원",
                            targetText = convertDiscountPrice
                        ),
                        color = Theme.colorScheme.darkGray,
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(dp5))
                    CardWidget(
                        containerColor = Theme.colorScheme.pureGray,
                        cornerShape = RoundedCornerShape(dp5),
                        innerPadding = PaddingValues(dp5)
                    ) {
                        Text(
                            text = stringResource(id = R.string.str_coupon_discount, coupon),
                            color = Theme.colorScheme.darkGray,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }


                    Spacer(modifier = Modifier.height(dp5))
                    Text(
                        text = stringResource(id = R.string.str_inTime_outTime, inTime, outTime),
                        color = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            if (info.isNotEmpty() || addInfo.isNotEmpty()) {
                Spacer(modifier = Modifier.height(dp20))

                CardWidget(
                    modifier = Modifier.fillMaxWidth(),
                    innerPadding = PaddingValues(dp10),
                    containerColor = Theme.colorScheme.pureGray,
                    cornerShape = RoundedCornerShape(dp8)
                ) {
                    Column {
                        if (info.isNotEmpty()) {
                            RowTwoWidget(
                                leftText = "객실정보",
                                rightText = info,
                                leftColor = Theme.colorScheme.gray,
                                rightColor = Theme.colorScheme.darkGray,
                                endPadding = dp15
                            )
                        }
                        if (addInfo.isNotEmpty()) {
                            RowTwoWidget(
                                modifier = Modifier.padding(top = dp5),
                                leftText = "추가정보",
                                rightText = addInfo,
                                leftColor = Theme.colorScheme.gray,
                                rightColor = Theme.colorScheme.darkGray,
                                endPadding = dp15
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewStayDetailItemWidget() {
    StayDetailItemWidget(
        RoomItem(
            name = "패밀리 스위트",
            info = "기준4인 * 최대6인",
            addInfo = "배드 : 퀸2 / 최대인원 초과 불가능",
            discountPer = 50,
            defaultPrice = "1100000",
            discountPrice = "554000",
            inTime = "15:00",
            outTime = "11:00",
            coupon = "6%+3000원",
            count = 2,
            image = ""
        )
    )
}
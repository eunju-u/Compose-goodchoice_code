package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.R
import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil

@Composable
fun KoreaStayItemWidget(
    stayItem: StayItem,
    clickLike: () -> Unit = {},
    onItemClick: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val modifier = Modifier.fillMaxWidth()
    var itemWidthModifier by remember { mutableStateOf(modifier) }
    var isLike by remember { mutableStateOf(true) }

    //해상도에 따라 호텔 item view 가로 조정
    LaunchedEffect(key1 = screenWidth) {
        itemWidthModifier = if (screenWidth > dp550) {
            Modifier.width(screenWidth / 1.5f)
        } else {
            modifier
        }
    }

    val painter =
        if (stayItem.mainImage?.isNotEmpty() == true)
            rememberAsyncImagePainter(
                model = stayItem.mainImage,
                painterResource(id = R.drawable.bg_white)
            )
        else painterResource(id = R.drawable.bg_white)

    CardWidget(
        modifier = Modifier
                then (itemWidthModifier)
            .height(IntrinsicSize.Min),
        isVisibleShadow = true,
        outerPadding = PaddingValues(dp10),
        innerPadding = PaddingValues(top = dp20, start = dp20, end = dp20, bottom = dp20),
        cornerShape = RoundedCornerShape(dp15),
        containerColor = Theme.colorScheme.white,
        shadowColor = Theme.colorScheme.pureGray,
        onItemClick = {
            onItemClick()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            val label = stayItem.label ?: ""
            val name = stayItem.name ?: ""
            val discountPer = stayItem.discountPer ?: 0
            val defaultPrice = stayItem.defaultPrice ?: ""
            val discountPrice = stayItem.discountPrice ?: "" //다른 날짜 확인 of 120000
            val isDefaultPriceNumber = defaultPrice.toIntOrNull() != null // 숫자로 변경 가능
            val isDiscountPriceNumber = discountPrice.toIntOrNull() != null // 숫자로 변경 가능

            val convertDefaultPrice =
                if (isDefaultPriceNumber) ConvertUtil.convertCommaString(defaultPrice) else defaultPrice
            val convertDiscountPrice =
                if (isDiscountPriceNumber) ConvertUtil.convertCommaString(discountPrice) else discountPrice

            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    color = Theme.colorScheme.gray,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(dp8))
            }

            if (name.isNotEmpty()) {
                Text(
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(dp8))
            }

            if (!stayItem.star.isNullOrEmpty() || !stayItem.location.isNullOrEmpty()) {
                Row(
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
                Spacer(modifier = Modifier.height(dp15))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundImageWidget(
                    imageModifier = Modifier
                        .width(screenWidth / 3)
                        .height(screenWidth / 2.6f),
                    roundShape = dp10,
                    painter = painter,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = "호텔 사진",
                    boxAlignment = Alignment.TopEnd,
                    content = {
                        IconButton(
                            content = {
                                Icon(
                                    modifier = Modifier.size(dp20),
                                    imageVector =
                                    if (isLike) ImageVector.vectorResource(id = R.drawable.ic_like_full)
                                    else ImageVector.vectorResource(id = R.drawable.ic_like),
                                    tint =
                                    if (isLike) Theme.colorScheme.red else Theme.colorScheme.pureGray,
                                    contentDescription = "하트"
                                )
                            },
                            onClick = {
                                clickLike()
                                isLike = !isLike
                            })
                    }
                )

                Column(horizontalAlignment = Alignment.End) {
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
                    Text(
                        text = convertDiscountPrice,
                        color = Theme.colorScheme.darkGray,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

            }
        }
    }
}
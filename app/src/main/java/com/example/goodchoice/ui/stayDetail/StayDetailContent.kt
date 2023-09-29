package com.example.goodchoice.ui.stayDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.isPopupLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.StayDetailData
import com.example.goodchoice.ui.components.RightImageButtonWidget
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.components.SpaceBetweenRowWidget
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.ui.stayDetail.widget.InfoWidget
import com.example.goodchoice.ui.stayDetail.widget.StayDetailItemWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil
import com.example.goodchoice.utils.StringUtil

@Composable
fun StayDetailContent(
    viewModel: StayDetailViewModel,
    onFinish: () -> Unit = {}
) {
    val detailUiState = viewModel.detailUiState.collectAsStateWithLifecycle()

    Box {
        LazyColumn(modifier = Modifier.background(Theme.colorScheme.white)) {
            if (detailUiState.value is ConnectInfo.Available) {
                val item = (detailUiState.value as ConnectInfo.Available).data as StayDetailData
                val padding = PaddingValues(start = dp20, end = dp20)
                val innerPadding = PaddingValues(horizontal = dp8, vertical = dp8)
                item {
                    //숙소 사진
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dp300)
                            .clickable {},
                        contentScale = ContentScale.FillHeight,
                        painter = if (item.imageList?.isNotEmpty() == true)
                            rememberAsyncImagePainter(
                                model = item.imageList[0], painterResource(id = R.drawable.bg_white)
                            )
                        else painterResource(id = R.drawable.bg_white),
                        contentDescription = "이미지",
                    )

                    Spacer(modifier = Modifier.height(dp25))

                    //성급
                    Text(
                        modifier = Modifier.padding(padding),
                        text = item.level ?: "",
                        color = Theme.colorScheme.panoramaBlue,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(dp5))

                    //숙소 이름
                    Text(
                        modifier = Modifier.padding(padding),
                        text = item.name ?: "",
                        color = Theme.colorScheme.darkGray,
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(dp5))

                    //위치
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(padding),
                        horizontalArrangement = Arrangement.spacedBy(dp5),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(dp5)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_around),
                                colorFilter = ColorFilter.tint(color = Theme.colorScheme.blue),
                                contentDescription = "위치"
                            )
                            Text(
                                text = item.location ?: "",
                                color = Theme.colorScheme.darkGray,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                        RightImageButtonWidget(
                            title = stringResource(id = R.string.str_show_map),
                            contentColor = Theme.colorScheme.blue,
                            style = MaterialTheme.typography.labelMedium,
                            imageColor = Theme.colorScheme.gray,
                            endPadding = dp0,
                            innerPadding = innerPadding,
                            imageSize = dp15,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(padding),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!item.star.isNullOrEmpty()) {
                            Image(
                                modifier = Modifier.size(dp20),
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = "별점"
                            )
                            Text(
                                text = item.star,
                                color = Theme.colorScheme.gray,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            val commentCount = item.commentCount ?: 0
                            if (commentCount > 0) {
                                RightImageButtonWidget(
                                    title = stringResource(
                                        id = R.string.str_see_review,
                                        "$commentCount"
                                    ),
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    contentColor = Theme.colorScheme.blue,
                                    imageColor = Theme.colorScheme.gray,
                                    endPadding = dp0,
                                    innerPadding = innerPadding,
                                    imageSize = dp15,
                                )
                            }
                            Spacer(modifier = Modifier.height(dp20))
                        }
                    }
                    if (!item.coupon.isNullOrEmpty()) {
                        SpaceBetweenRowWidget(
                            modifier = Modifier
                                .fillMaxWidth(),
                            isPadding = true,
                            padding = dp20,
                            text = StringUtil.setTextColor(
                                originText = stringResource(
                                    id = R.string.str_max_add_discount,
                                    item.coupon
                                ), targetText = item.coupon, color = Theme.colorScheme.red
                            ), textColor = Theme.colorScheme.darkGray,
                            textStyle = MaterialTheme.typography.labelMedium,
                            content = {
                                RightImageButtonWidget(
                                    title = stringResource(id = R.string.str_download_coupon),
                                    contentColor = Theme.colorScheme.white,
                                    containerColor = Theme.colorScheme.blue,
                                    style = MaterialTheme.typography.labelMedium,
                                    innerPadding = innerPadding,
                                    imageSize = dp15,
                                    content = {
                                        Image(
                                            modifier = Modifier.size(dp15),
                                            painter = painterResource(id = R.drawable.ic_download),
                                            colorFilter = ColorFilter.tint(color = Theme.colorScheme.white),
                                            contentDescription = "쿠폰"
                                        )
                                    })
                            }
                        )
                        Spacer(modifier = Modifier.height(dp15))
                    }
                    Divider(thickness = dp10, color = Theme.colorScheme.pureGray)

                    if (!item.payList.isNullOrEmpty()) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                            .padding(padding)
                            .padding(top = dp20, bottom = dp20)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(modifier = Modifier.weight(1f, false)) {
                                    RoundImageWidget(
                                        imageModifier = Modifier
                                            .size(dp20),
                                        painter = painterResource(id = R.drawable.bg_gray),
                                        roundShape = dp30,
                                        boxAlignment = Alignment.Center,
                                        content = {
                                            Image(
                                                modifier = Modifier.size(dp15),
                                                painter = painterResource(id = R.drawable.ic_won),
                                                contentDescription = null
                                            )

                                        }
                                    )
                                    Text(
                                        modifier = Modifier.padding(start = dp10),
                                        text = stringResource(id = R.string.str_pay_benefit),
                                        color = Theme.colorScheme.darkGray,
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                                Text(
                                    text = stringResource(id = R.string.str_more),
                                    color = Theme.colorScheme.blue,
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            Spacer(modifier = Modifier.heightIn(dp15))
                            val firstPayType =
                                ConvertUtil.convertPayImage(item.payList.first().payType ?: "")
                            Row {
                                Image(
                                    modifier = Modifier
                                        .width(dp60)
                                        .padding(end = dp10),
                                    painter = painterResource(id = firstPayType),
                                    contentDescription = null
                                )
                                Text(
                                    text = item.payList.first().payName ?: "",
                                    color = Theme.colorScheme.darkGray,
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            Spacer(modifier = Modifier.heightIn(dp5))

                            item.payList[0].payInfoList?.run {
                                this.forEachIndexed { index, item ->
                                    val infoPadding =
                                        if (index == 0 || index == this.lastIndex) dp0 else dp3
                                    InfoWidget(
                                        modifier = Modifier.padding(
                                            top = infoPadding,
                                            bottom = infoPadding
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        isCenter = true,
                                        leftColor = Theme.colorScheme.gray,
                                        rightColor = Theme.colorScheme.gray,
                                        leftStyle = MaterialTheme.typography.labelMedium,
                                        rightStyle = MaterialTheme.typography.labelMedium,
                                        leftText = "*", rightText = StringUtil.setTextLine(
                                            originText = item.payInfo ?: "",
                                            targetText = item.payLineTest ?: ""
                                        )
                                    )
                                }
                            }
                        }
                    }

                    if (!item.roomList.isNullOrEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Theme.colorScheme.pureGray)
                        ) {

                            item.roomList.forEach { item ->
                                StayDetailItemWidget(item)
                            }
                        }
                    }
                }
            }
        }

        TopAppBarWidget(
            onFinish = onFinish,
            isAlpha = true,
            iconColor = Theme.colorScheme.white,
            rightContent = {
                Row {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier
                                .size(dp20),
                            painter = painterResource(id = R.drawable.ic_share),
                            tint = Theme.colorScheme.pureGray,
                            contentDescription = "공유"
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier
                                .size(dp20),
                            painter = painterResource(id = R.drawable.ic_like),
                            tint = Theme.colorScheme.pureGray,
                            contentDescription = "하트"
                        )
                    }
                }
            })
    }


}
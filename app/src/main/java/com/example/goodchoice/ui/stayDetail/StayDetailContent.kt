package com.example.goodchoice.ui.stayDetail

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.Const
import com.example.goodchoice.DialogType
import com.example.goodchoice.R
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.components.*
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetLayout
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetState
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetValue
import com.example.goodchoice.ui.components.bottomSheet.SheetWidget
import com.example.goodchoice.ui.components.bottomSheet.rememberMyBottomSheetState
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.stayDetail.service.ServiceActivity
import com.example.goodchoice.ui.stayDetail.widget.PayWidget
import com.example.goodchoice.ui.stayDetail.widget.StayDetailItemWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil
import com.example.goodchoice.utils.StringUtil
import com.example.goodchoice.utils.ToastUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StayDetailContent(
    viewModel: StayDetailViewModel,
    onFinish: () -> Unit = {}
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val configuration = LocalConfiguration.current
    val fullWith = configuration.screenWidthDp
    val lazyColumnListState = rememberLazyListState()

    val detailUiState = viewModel.detailUiState.collectAsStateWithLifecycle()

    val isShowDialog = viewModel.isShowDialog.collectAsStateWithLifecycle()
    val dialogType = viewModel.dialogType.collectAsStateWithLifecycle()

    //바텀시트
    val sheetState: MyBottomSheetState =
        rememberMyBottomSheetState(
            initialValue = MyBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    //상단 app bar 숙소 이름 노출 및 색상 변경 플래그
    val isChangeTopWidget by remember {
        derivedStateOf {
            lazyColumnListState.firstVisibleItemIndex > 0
        }
    }

    val rememberScope = rememberCoroutineScope()


    val toastStr = if (!viewModel.isLike.value) {
        stringResource(id = R.string.str_add_like)
    } else stringResource(id = R.string.str_remove_like)

    Box {
        if (detailUiState.value is StayDetailConnectInfo.Available) {
            val item = (detailUiState.value as StayDetailConnectInfo.Available).data
            val padding = PaddingValues(start = dp20, end = dp20)
            val innerPadding = PaddingValues(horizontal = dp8, vertical = dp8)
            LazyColumn(
                modifier = Modifier.background(Theme.colorScheme.white), state = lazyColumnListState
            ) {
                item {
                    if (!item.imageList.isNullOrEmpty()) {
                        //숙소 사진
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dp300)
                                .clickable {},
                            contentScale = ContentScale.FillHeight,
                            painter = if (item.imageList.isNotEmpty())
                                rememberAsyncImagePainter(
                                    model = item.imageList[0],
                                    painterResource(id = R.drawable.bg_white)
                                )
                            else painterResource(id = R.drawable.bg_white),
                            contentDescription = "이미지",
                        )
                    }

                }
                item {
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
                    if (!item.location.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding),
                            horizontalArrangement = Arrangement.spacedBy(dp5),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.spacedBy(dp5),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_around),
                                    colorFilter = ColorFilter.tint(color = Theme.colorScheme.blue),
                                    contentDescription = "위치"
                                )
                                Text(
                                    text = item.location,
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
                                ), targetText = item.coupon, targetColor = Theme.colorScheme.red
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
                            .clickable {
                                rememberScope.launch {
                                    sheetState.show()
                                }
                            }
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
                                    RowTwoWidget(
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
                    if (!item.message.isNullOrEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding)
                                .padding(top = dp20, bottom = dp20)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_left_quote),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.pureBlue),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(top = dp15, bottom = dp15),
                                text = item.message,
                                color = Theme.colorScheme.gray,
                                style = MaterialTheme.typography.labelMedium
                            )
                            Image(
                                modifier = Modifier.align(Alignment.End),
                                painter = painterResource(id = R.drawable.ic_right_quote),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.pureBlue),

                                contentDescription = null
                            )
                        }
                    }

                    if (!item.service.isNullOrEmpty()) {
                        Divider(thickness = dp10, color = Theme.colorScheme.pureGray)

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    Intent(context, ServiceActivity::class.java).apply {
                                        putExtra(Const.DATA, item.service)
                                    }
                                )
                            }
                            .padding(padding)
                            .padding(top = dp20, bottom = dp20)
                        ) {
                            SpaceBetweenRowWidget(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.str_convenience_service),
                                content = {
                                    Text(
                                        text = stringResource(id = R.string.str_detail_more),
                                        color = Theme.colorScheme.blue,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                })

                            //(가로길이 - start padding 20 - end padding 20) / 아이템 width 70
                            val count = (fullWith - 40) / 70
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = dp20),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                if (item.service.size > count) {
                                    for (i in 0 until count) {
                                        val serviceData = item.service[i]
                                        CategoryItemWidget(
                                            width = dp70,
                                            imageSize = dp45,
                                            bottomPadding = dp15,
                                            textStyle = MaterialTheme.typography.labelMedium,
                                            colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                            painter =
                                            painterResource(
                                                id = ConvertUtil.convertServiceImage(
                                                    serviceData.type ?: ""
                                                )
                                            ),
                                            name = serviceData.name ?: ""
                                        )
                                    }
                                } else {
                                    item.service.forEach { serviceData ->
                                        CategoryItemWidget(
                                            imageSize = dp60,
                                            bottomPadding = dp15,
                                            textStyle = MaterialTheme.typography.labelMedium,
                                            colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                            painter =
                                            painterResource(
                                                id = ConvertUtil.convertServiceImage(
                                                    serviceData.type ?: ""
                                                )
                                            ),
                                            name = serviceData.name ?: ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 상단 바
        TopAppBarWidget(
            title = if (isChangeTopWidget) viewModel.stayItemTitle else "",
            onFinish = onFinish,
            isAlpha = !isChangeTopWidget,
            iconColor = if (isChangeTopWidget) Theme.colorScheme.darkGray else Theme.colorScheme.white,
            rightContent = {
                Row {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier
                                .size(dp20),
                            painter = painterResource(id = R.drawable.ic_share),
                            tint = if (isChangeTopWidget) Theme.colorScheme.darkGray else Theme.colorScheme.pureGray,
                            contentDescription = "공유"
                        )
                    }
                    IconButton(onClick = {
                        if (pref.isLogin) {
                            viewModel.isLike.value = !viewModel.isLike.value
                            ToastUtils.showToast(toastStr)
                        } else {
                            viewModel.isShowDialog.value = true
                            viewModel.dialogType.value = DialogType.NEED_LOGIN
                        }

                    }) {
                        Icon(
                            modifier = Modifier
                                .size(dp20),
                            painter =
                            if (viewModel.isLike.value) painterResource(id = R.drawable.ic_like_full)
                            else painterResource(id = R.drawable.ic_like),
                            tint =
                            if (viewModel.isLike.value) Theme.colorScheme.red else if (isChangeTopWidget) Theme.colorScheme.darkGray else Theme.colorScheme.pureGray,
                            contentDescription = "하트"
                        )
                    }
                }
            })

        // 바텀시트
        MyBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                SheetWidget(
                    shape = RoundedCornerShape(topStart = dp30, topEnd = dp30),
                    content = {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(dp20)
                        ) {
                            Text(
                                modifier = Modifier.padding(top = dp15, bottom = dp15),
                                text = stringResource(id = R.string.str_pay_benefit_information),
                                color = Theme.colorScheme.darkGray,
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                            )

                            LazyColumn {
                                items(items = viewModel.payList) { item ->
                                    PayWidget(item)
                                }
                            }
                        }
                    })
            })

        if (isShowDialog.value) {
            when (dialogType.value) {
                DialogType.NEED_LOGIN -> {
                    AlertDialogWidget(
                        title = stringResource(id = R.string.str_need_login),
                        oneButtonText = stringResource(id = R.string.str_close),
                        twoButtonText = stringResource(id = R.string.str_login),
                        onDismiss = {
                            viewModel.isShowDialog.value = false
                        },
                        onConfirm = {
                            viewModel.isShowDialog.value = false
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        })
                }


                DialogType.NETWORK_ERROR -> {
                    AlertDialogWidget(
                        onDismiss = {},
                        title = stringResource(id = R.string.str_dialog_network_not_connect),
                        oneButtonText = stringResource(id = R.string.str_ok),
                        onConfirm = {
                            viewModel.isShowDialog.value = false
                            onFinish()
                        },
                    )
                }
                else -> {}
            }
        }

        // 로딩바
        if (detailUiState.value is StayDetailConnectInfo.Loading) {
            LoadingWidget()
        }
    }
}
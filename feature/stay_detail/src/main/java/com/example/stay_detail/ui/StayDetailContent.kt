package com.example.stay_detail.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import com.example.common.Const
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.ui_common.R
import com.example.ui_theme.*
import com.example.data.local.preference.GoodChoicePreference
import com.example.stay_detail.ui.service.ServiceActivity
import com.example.stay_detail.ui.widget.PayWidget
import com.example.stay_detail.ui.widget.StayDetailItemWidget
import com.example.common.utils.ToastUtil
import com.example.stay_detail.ui.state.StayDetailUiState
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.CategoryItemWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.components.RightImageButtonWidget
import com.example.ui_common.components.RoundImageWidget
import com.example.ui_common.components.RowTwoWidget
import com.example.ui_common.components.SpaceBetweenRowWidget
import com.example.ui_common.components.TopAppBarWidget
import com.example.ui_common.components.bottomSheet.MyBottomSheetLayout
import com.example.ui_common.components.bottomSheet.MyBottomSheetState
import com.example.ui_common.components.bottomSheet.MyBottomSheetValue
import com.example.ui_common.components.bottomSheet.SheetWidget
import com.example.ui_common.components.bottomSheet.rememberMyBottomSheetState
import com.example.ui_common.utils.ConvertUtil
import com.example.ui_common.utils.StringUtil
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
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

    val stayDetailUiState by viewModel.stayDetailUiState.collectAsState()

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
        if (stayDetailUiState is StayDetailUiState.Success) {
            val item = (stayDetailUiState as StayDetailUiState.Success).data
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
                            painter = item.imageList?.let {
                                if (it.isNotEmpty()) {
                                    rememberAsyncImagePainter(
                                        model = it[0], painterResource(id = R.drawable.bg_white)
                                    )
                                } else {
                                    painterResource(id = R.drawable.bg_white)
                                }
                            } ?: painterResource(id = R.drawable.bg_white),
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
                                text = item.star ?: "",
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
                                    item.coupon ?: ""
                                ),
                                targetText = item.coupon ?: "",
                                targetColor = Theme.colorScheme.red
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
                    HorizontalDivider(thickness = dp10, color = Theme.colorScheme.pureGray)

                    item.payList?.let {
                        if (it.isNotEmpty()) {
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
                                    ConvertUtil.convertPayImage(it.first().payType ?: "")
                                Row {
                                    Image(
                                        modifier = Modifier
                                            .width(dp60)
                                            .padding(end = dp10),
                                        painter = painterResource(id = firstPayType),
                                        contentDescription = null
                                    )
                                    Text(
                                        text = it.first().payName ?: "",
                                        color = Theme.colorScheme.darkGray,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                }
                                Spacer(modifier = Modifier.heightIn(dp5))

                                it[0].payInfoList?.run {
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
                    }

                    item.roomList?.let {
                        if (it.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Theme.colorScheme.pureGray)
                            ) {
                                it.forEach { item ->
                                    StayDetailItemWidget(item)
                                }
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
                                text = item.message ?: "",
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

                    item.service?.let {
                        if (it.isNotEmpty()) {
                            HorizontalDivider(thickness = dp10, color = Theme.colorScheme.pureGray)

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
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold
                                            )
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
                                    if (it.size > count) {
                                        for (i in 0 until count) {
                                            val serviceData = it[i]
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
                                        it.forEach { serviceData ->
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
                            ToastUtil.showToast(context, toastStr)
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
                    shape = RoundedCornerShape(
                        topStart = dp30,
                        topEnd = dp30
                    ),
                    content = {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(dp20)
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    top = dp15,
                                    bottom = dp15
                                ),
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
                            context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("feature://login")
                            })
                        })
                }


                DialogType.NETWORK_ERROR -> {
                    AlertDialogWidget(
                        onDismiss = {},
                        title = stringResource(id = R.string.str_dialog_network_not_connect),
                        oneButtonText = stringResource(id = R.string.str_ok),
                        onConfirm = {
                            viewModel.isShowDialog.value = false
                            viewModel.sendIntent(CommonIntent.Retry("reload"))
                        },
                    )
                }

                else -> {}
            }
        }

        when (stayDetailUiState) {
            is StayDetailUiState.Loading -> LoadingWidget()
            is StayDetailUiState.Error -> {
                viewModel.dialogType.value = DialogType.NETWORK_ERROR
                viewModel.isShowDialog.value = true
            }
            else -> {}
        }
    }
}
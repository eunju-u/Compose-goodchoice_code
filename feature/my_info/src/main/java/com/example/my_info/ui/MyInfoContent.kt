package com.example.my_info.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.Const
import com.example.common.DialogType
import com.example.ui_common.R
import com.example.common.MainBottomSheetType
import com.example.common.intent.CommonIntent
import com.example.ui_theme.*
import com.example.data.local.preference.GoodChoicePreference
import com.example.my_info.ui.detail.MyInfoDetailActivity
import com.example.my_info.ui.state.MyInfoUiState
import com.example.my_info.ui.widget.CouponWidget
import com.example.my_info.ui.widget.MenuItemWidget
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.CategoryItemWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.components.SpaceBetweenRowWidget
import com.example.ui_common.components.TextWidget

/**
 * 내 정보 화면
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun MyInfoContent(
    modifier: Modifier = Modifier,
    viewModel: MyInfoViewModel,
    showBottomSheet: (type: MainBottomSheetType) -> Unit = {},
) {

    //액티비티 호출
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val isLogin = pref.isLogin
    val userName = pref.userName

    val myInfoState by viewModel.myInfoState.collectAsState()

    val lazyColumnListState = rememberLazyListState()

    val isShowDialog = viewModel.isShowDialog.collectAsStateWithLifecycle()
    val dialogType = viewModel.dialogType.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ) {
            item {
                if (myInfoState is MyInfoUiState.Success) {

                    val myInfoData = (myInfoState as MyInfoUiState.Success).list
                    val topMenuList = myInfoData.topMenuList ?: emptyList()
                    val menuList = myInfoData.menuList ?: emptyList()

                    CardWidget(
                        isVisibleShadow = true,
                        shadowOffsetY = dp20,
                        shadowColor = Theme.colorScheme.pureGray,
                        innerPadding = PaddingValues(start = dp20, end = dp20, top = dp20),
                        containerColor = Theme.colorScheme.white,
                        content = {
                            /** 내 정보 > 상단 내 정보 (임시) */
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val topText =
                                    if (isLogin) userName else stringResource(id = R.string.str_join_and_login)
                                val subText =
                                    if (isLogin) "엘리트 회원" else stringResource(id = R.string.str_join_and_login_sub)

                                val iconModifier = if (isLogin) {
                                    Modifier.clickable {
                                        //TODO 사진 로직 추가

                                    }
                                } else Modifier


                                /** 이미지 및 회원가입/로그인 버튼 */
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Min),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(dp80)
                                            .then(iconModifier)
                                            .clickable {
                                                if (isLogin) {
                                                    showBottomSheet(MainBottomSheetType.PROFILE)
                                                }
                                            },
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_smile),
                                        tint = Theme.colorScheme.red,
                                        contentDescription = "내 정보",
                                    )

                                    SpaceBetweenRowWidget(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(dp10)
                                            .clickable {
                                                if (isLogin) {
                                                    context.startActivity(
                                                        Intent(
                                                            context,
                                                            MyInfoDetailActivity::class.java
                                                        )
                                                    )
                                                } else {
                                                    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                                        data = Uri.parse("feature://login")
                                                    })
                                                }
                                            },
                                        content = {
                                            Column(
                                                modifier = Modifier.weight(1f),
                                            ) {
                                                TextWidget(
                                                    textAlign = TextAlign.Start,
                                                    text = topText,
                                                    style = MaterialTheme.typography.displaySmall,
                                                )
                                                TextWidget(
                                                    modifier = Modifier
                                                        .padding(top = dp5)
                                                        .background(Theme.colorScheme.mediumPurple)
                                                        .padding(dp3),
                                                    textAlign = TextAlign.Start,
                                                    color = Theme.colorScheme.darkPurple,
                                                    maxLines = 2,
                                                    text = subText,
                                                    style = MaterialTheme.typography.labelMedium
                                                )
                                            }
                                            if (isLogin) {
                                                Icon(
                                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_right),
                                                    tint = Theme.colorScheme.darkGray,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    )
                                }

                                /** 포인트/쿠폰 버튼 */
                                CouponWidget(onLeftClick = {}, onRightClick = {})

                                /** 카테고리 (최근 본 상품, 할인*혜택, 내 리뷰, 알림함) 버튼 */
                                if (topMenuList.isNotEmpty()) {
                                    val weigh = (100 / topMenuList.size) * 0.01

                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        items(items = topMenuList) { item ->
                                            Column(
                                                modifier = Modifier
                                                    .fillParentMaxWidth(weigh.toFloat())
                                                    .clickable { }
                                                    .padding(top = dp20, bottom = dp20),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                CategoryItemWidget(
                                                    imageSize = dp20,
                                                    painter = painterResource(
                                                        id = item.icon ?: R.drawable.bg_white
                                                    ),
                                                    name = item.name ?: "",
                                                    bottomPadding = dp10,
                                                    colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                                                    onItemClick = {
                                                        if (item.code == Const.RECENT_HOTEL) {
                                                            context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                                                data =
                                                                    Uri.parse("feature://recent_seen")
                                                            })
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    )

                    if (menuList.isNotEmpty()) {
                        /** 예약내역*/
                        // items(menuList) 로 했을 때 스크롤시 버벅임!!
                        // LazyColumn 안에 리스트 성 위젯 말고 다른 위젯이 포함되어있다면 for 문 써서 하기
                        Spacer(modifier = Modifier.padding(top = dp15))
                        menuList.forEachIndexed { index, item ->
                            Column {
                                if (item.title != "") {
                                    TextWidget(
                                        modifier = Modifier.padding(
                                            start = dp25,
                                            end = dp25,
                                            bottom = dp15,
                                            top = dp15
                                        ),
                                        text = item.title,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = Theme.colorScheme.darkGray
                                    )
                                }

//                        if (it.path != ""){
//                            Image(
//                                modifier = Modifier.fillMaxWidth(),
//                                painter = painterResource(id = R.drawable.bg_purple),
//                                contentDescription = null
//                            )
//                        }

                                item.list?.let {
                                    it.forEach { menuItem ->
                                        var isShow = true
                                        val isLogoutMenu = menuItem.name == Const.LOGOUT
                                        if (!pref.isLogin && isLogoutMenu) {
                                            isShow = false
                                        }

                                        if (isShow) {
                                            MenuItemWidget(
                                                menuItem = menuItem,
                                                isOnlyText = isLogoutMenu,
                                                onItemClick = {
                                                    if (isLogoutMenu) {
                                                        viewModel.dialogType.value = DialogType.NEED_LOGIN
                                                        viewModel.isShowDialog.value = true
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }

                                if (index != menuList.lastIndex) {
                                    //선
                                    HorizontalDivider(
                                        modifier = Modifier.padding(top = dp15, bottom = dp15),
                                        thickness = dp2,
                                        color = Theme.colorScheme.pureGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (isShowDialog.value) {
            when (dialogType.value) {
                DialogType.NEED_LOGIN -> {
                    AlertDialogWidget(
                        title = stringResource(id = R.string.str_logout_check),
                        oneButtonText = stringResource(id = R.string.str_cancel),
                        twoButtonText = stringResource(id = R.string.str_logout),
                        onDismiss = { },
                        onConfirm = {
                            pref.isLogin = false
                            viewModel.isShowDialog.value = false
                            viewModel.sendIntent(CommonIntent.Retry("logout"))
                        })
                }

                DialogType.NETWORK_ERROR -> {
                    AlertDialogWidget(
                        onDismiss = { },
                        title = stringResource(id = R.string.str_dialog_network_not_connect),
                        onConfirm = {
                            viewModel.isShowDialog.value = false
                            viewModel.sendIntent(CommonIntent.Retry("reload"))
                        },
                        oneButtonText = stringResource(id = R.string.str_ok),
                    )
                }

                else -> {}
            }
        }

        when (myInfoState) {
            is MyInfoUiState.Loading -> LoadingWidget()
            is MyInfoUiState.Error -> {
                viewModel.dialogType.value = DialogType.NETWORK_ERROR
                viewModel.isShowDialog.value = true
            }
            else -> {}
        }
    }

}
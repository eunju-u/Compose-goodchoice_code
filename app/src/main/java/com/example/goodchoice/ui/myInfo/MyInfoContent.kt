package com.example.goodchoice.ui.myInfo

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.Const
import com.example.ui.R
import com.example.common.MainBottomSheetType
import com.example.ui.components.*
import com.example.ui_theme.*
import com.example.domain.info.ConnectInfo
import com.example.domain.model.CategoryItem
import com.example.domain.model.MyMenuData
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui.login.LoginActivity
import com.example.goodchoice.ui.main.MainActivity
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.myInfo.detail.MyInfoDetailActivity
import com.example.goodchoice.ui.myInfo.widget.CouponWidget
import com.example.goodchoice.ui.myInfo.widget.MenuItemWidget
import com.example.ui.recentSeen.RecentSeenActivity

/**
 * 내 정보 화면
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyInfoContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    //액티비티 호출
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val pref = GoodChoicePreference(context)
    val isLogin = pref.isLogin
    val userName = pref.userName

    //가져올때
    val myInfoData = viewModel.myInfoData.collectAsStateWithLifecycle().value
    val topMenuList: List<CategoryItem> = myInfoData.topMenuList ?: emptyList()
    val menuList: List<MyMenuData> = myInfoData.menuList ?: emptyList()

    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val lazyColumnListState = rememberLazyListState()
    var isShowDialog by remember { mutableStateOf(false) }

    //미정님 myInfo 에서 설정 화면이 안나오는 이유는 Box의 modifier 을 Modifier로 새로 정의해서 그래요!
    //MyInfoContent 의 modifier 을 써야 해요
    //MyInfoContent 의 modifier 는 main 에서 navi bottom 뷰 제외하고 준거라 크기가 맞게 됩니다~
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ) {
            item {
                if (homeUiState.value is ConnectInfo.Available) {
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
                                                    viewModel.bottomSheetType.value = MainBottomSheetType.PROFILE
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
                                                    (context as MainActivity).startActivity(
                                                        Intent(
                                                            context,
                                                            MyInfoDetailActivity::class.java
                                                        )
                                                    )
                                                } else {
                                                    (context as MainActivity).startActivity(
                                                        Intent(
                                                            context,
                                                            LoginActivity::class.java
                                                        )
                                                    )
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
                                                            context.startActivity(
                                                                Intent(
                                                                    context,
                                                                    RecentSeenActivity::class.java
                                                                )
                                                            )
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
                                                        isShowDialog = true
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
    }
    if (isShowDialog) {
        AlertDialogWidget(
            title = stringResource(id = R.string.str_logout_check),
            oneButtonText = stringResource(id = R.string.str_cancel),
            twoButtonText = stringResource(id = R.string.str_logout),
            onDismiss = { isShowDialog = false },
            onConfirm = {
                pref.isLogin = false
                viewModel.requestMyInfoData()
                isShowDialog = false
            })
    }
}
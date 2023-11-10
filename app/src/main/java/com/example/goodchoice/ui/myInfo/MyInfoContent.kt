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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.api.data.CategoryItem
import com.example.goodchoice.api.data.MyMenuData
import com.example.goodchoice.ui.components.*
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.myInfo.widget.CouponWidget
import com.example.goodchoice.ui.myInfo.widget.MenuItemWidget
import com.example.goodchoice.ui.theme.GMarketSansFamily
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.*

/**
 * 내 정보 화면
 */

/*
* 0. @Composable Android compose 앱의 UI를 구성할 때 사용
* 1. Column : LinearLayout , 수직 아이템 나열, 아이템 너비 Column 너비
* 2. Row : 가로방향 나열
* 3. Box : 겹쳐서 배치하는 레이아웃, z축
* 4. Scaffold : 기초적인 meteria 디자인 레이아웃 구조 , topbar bottombar snackbar, floatingActionbutton, drwaer 슬롯
* 5. BottomNavigation :
* 6. BottomNavigationItem :
* 7. Icon :
* 8. BackHandler :
* 9. NavGraph :
*
* */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyInfoContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    //액티비티 호출
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    //가져올때
    val myInfoData = viewModel.myInfoData.collectAsStateWithLifecycle().value
    val topMenuList: List<CategoryItem> = myInfoData.topMenuList ?: emptyList()
    val menuList: List<MyMenuData> = myInfoData.menuList ?: emptyList()

    val lazyColumnListState = rememberLazyListState()

    //폰트 관련
    val style = TextStyle(
        fontSize = 12.sp,
        lineHeight = 10.sp,
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
    )

    //미정님 myInfo 에서 설정 화면이 안나오는 이유는 Box의 modifier 을 Modifier로 새로 정의해서 그래요!
    //MyInfoContent 의 modifier 을 써야 해요
    //MyInfoContent 의 modifier 는 main 에서 navi bottom 뷰 제외하고 준거라 크기가 맞게 됩니다~
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ) {
            item {
                CardWidget(
                    isVisibleShadow = true,
                    shadowOffsetY = dp20,
                    shadowColor = Theme.colorScheme.pureGray,
                    outerPadding = PaddingValues(bottom = dp20),
                    innerPadding = PaddingValues(vertical = dp20, horizontal = dp20),
                    containerColor = Theme.colorScheme.white,
                    content = {
                        /** 내 정보 > 상단 내 정보 (임시) */
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            /** 이미지 및 회원가입/로그인 버튼 */
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(dp90)
                                        .padding(dp10),
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_smile),
                                    tint = Theme.colorScheme.red,
                                    contentDescription = "내 정보"
                                )

                                SpaceBetweenRowWidget(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(dp10)
                                        .clickable {
                                            context.startActivity(
                                                Intent(
                                                    context,
                                                    LoginActivity::class.java
                                                )
                                            )
                                        },
                                    content = {
                                        Column(
                                            modifier = Modifier.weight(1f),
                                        ) {
                                            Text(
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.str_join_and_login),
                                                style = MaterialTheme.typography.displaySmall,
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = dp5)
                                                    .background(Theme.colorScheme.pureBlue)
                                                    .padding(dp5),
                                                textAlign = TextAlign.Start,
                                                color = Theme.colorScheme.blue,
                                                text = stringResource(id = R.string.str_join_and_login_sub),
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                        }
                                        Icon(
                                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_right),
                                            tint = Theme.colorScheme.darkGray,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }

                            /** 포인트/쿠폰 버튼 */
                            CouponWidget(onLeftClick = {}, onRightClick = {})

                            /** 카테고리 (최근 본 상품, 할인*혜택, 내 리뷰, 알림함) 버튼 */
                            if (topMenuList.isNotEmpty()) {
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = dp20, bottom = dp10),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    items(items = topMenuList) { item ->
                                        CategoryItemWidget(
                                            imageSize = dp20,
                                            painter = painterResource(
                                                id = item.icon ?: R.drawable.bg_white
                                            ),
                                            name = item.name ?: "",
                                            bottomPadding = dp10,
                                            colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                                            onItemClick = {}
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }

            if (menuList.isNotEmpty()) {
                /** 예약내역*/
                items(menuList) {
                    Column(
                        modifier = Modifier.padding(dp5)
                    ) {
                        if (it.title != "") {
                            TextWidget(
                                text = it.title,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

//                        if (it.path != ""){
//                            Image(
//                                modifier = Modifier.fillMaxWidth(),
//                                painter = painterResource(id = R.drawable.bg_purple),
//                                contentDescription = null
//                            )
//                        }

                        if (it.list!!.isNotEmpty()) {
                            for (listItem in it.list) {
                                MenuItemWidget(menuItem = listItem)
                            }
                        }
                    }
                }
            }
        }
    }


}
package com.example.goodchoice.ui.myInfo

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.api.data.CategoryItem
import com.example.goodchoice.api.data.MyMenuData
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.ui.components.CategoryItemWidget
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.components.RightImageButtonWidget
import com.example.goodchoice.ui.components.SpaceBetweenRowWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.main.MainViewModel
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
fun MyInfoContent(modifier: Modifier = Modifier, viewModel: MainViewModel){

    //액티비티 호출
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    //가져올때
    val myInfoData = viewModel.myInfoData.collectAsStateWithLifecycle().value
    val topMenuList : List<CategoryItem> = myInfoData.topMenuList ?: emptyList()
    val menuList : List<MyMenuData> = myInfoData.menuList ?: emptyList()

    val lazyColumnListState = rememberLazyListState()

    //폰트 관련
    val style = TextStyle(
        fontSize = 12.sp,
        lineHeight = 10.sp,
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
    )
    var textStyle by remember { mutableStateOf(style) }

    Box(modifier = Modifier){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ){
            item {
                /** 내 정보 > 상단 내 정보 (임시) */
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(Theme.colorScheme.pureGray)
                ) {
                    /** 이미지 및 회원가입/로그인 버튼 */
                    Row(modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(dp5)
                        .fillMaxWidth()
                        .background(Theme.colorScheme.white),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            modifier = Modifier
                                .size(dp90)
                                .padding(dp10),
                            painter = painterResource(id = R.drawable.bg_teal),
                            contentDescription = "내 정보"
                        )

                        SpaceBetweenRowWidget (
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
                                    modifier = Modifier
                                        .weight(1f),
                                ){
                                    Text(
                                        textAlign = TextAlign.Start,
                                        text = "회원가입/로그인",
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(top = dp5)
                                            .background(Theme.colorScheme.pureBlue),
                                        textAlign = TextAlign.Start,
                                        color = Theme.colorScheme.blue,
                                        text = "여기 어때 회원가입하고 엘리트 혜택 받으세요!",
                                        style = MaterialTheme.typography.bodySmall)
                                }
                                Image(
                                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                    painter = painterResource(id = R.drawable.ic_right),
                                    contentDescription = null
                                )
                            }
                        )
                    }

                    /** 포인트/쿠폰 버튼 */
//                    Surface(shape = MaterialTheme.shapes.medium, elevation = 10.dp) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .background(Theme.colorScheme.pureBlue),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            ButtonWidget(
                                modifier = Modifier.width(dp150),
                                shape = 0.dp,
//                                borderColor = Theme.colorScheme.gray,
                                onItemClick = { /*TODO*/ },
                                content = {Text(text = "포인트")}
                            )

                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(dp40),
                                color = Theme.colorScheme.gray
                            )

                            ButtonWidget(
                                modifier = Modifier.width(dp150),
                                shape = 0.dp,
//                                borderColor = Theme.colorScheme.gray,
                                onItemClick = { /*TODO*/ },
                                content = {Text(text = "쿠폰")}
                            )
                        }

//                    }

                    /** 카테고리 (최근 본 상품, 할인*혜택, 내 리뷰, 알림함) 버튼 */
                    // 카테고리 뷰
                    if (topMenuList.isNotEmpty()){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .selectableGroup()
                            .padding(top = dp10, bottom = dp10, start = dp30, end = dp30),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            for (i in topMenuList.indices) {
                                //텍스트, 이미지 사이즈 확인 필요
                                CategoryItemWidget(item = topMenuList[i])
                            }
                        }
                    }
                }
            }


            if (menuList.isNotEmpty()){
                /** 예약내역*/
                items(menuList){
                    Column(
                        modifier = Modifier.padding(dp5)
                    ) {
                        if (it.title != ""){
                            TextWidget(
                                text = it.title,
                                style = MaterialTheme.typography.titleLarge)
                        }

//                        if (it.path != ""){
//                            Image(
//                                modifier = Modifier.fillMaxWidth(),
//                                painter = painterResource(id = R.drawable.bg_purple),
//                                contentDescription = null
//                            )
//                        }

                        if(it.list!!.isNotEmpty()){
                            for (listItem in it.list){
                                MenuItemWidget(menuItem = listItem)
                            }
                        }
                    }
                }
            }
        }
    }



}
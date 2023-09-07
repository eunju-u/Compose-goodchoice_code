package com.example.goodchoice.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.StayData
import com.example.goodchoice.ui.components.CategoryItemWidget
import com.example.goodchoice.ui.home.widget.*
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.*

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeContent(
    modifier: Modifier = Modifier, viewModel: MainViewModel
) {
    val context = LocalContext.current
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val homeData = viewModel.homeData.value
    val categoryList = homeData.categoryList
    val bannerList = homeData.bannerList
    val stayList = homeData.stayList
    val recentStay = homeData.recentStay
    val overSeaCityList = homeData.overSeaCityList
    val row = 4

    val isShowFullHeader = viewModel.isShowFullHeader.collectAsStateWithLifecycle()
    val lazyColumnListState = rememberLazyListState()
    val textStyle = MaterialTheme.typography.displayMedium
    // header 노출하기 위한 플래그
    val isShowHeader by remember {
        derivedStateOf {
            lazyColumnListState.firstVisibleItemIndex > 1
        }
    }

    val scope = rememberCoroutineScope()
    val isRefresh = viewModel.isRefreshHomeData.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ) {
            item {
                HomeTopBarWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dp20, bottom = dp20),
                    title = stringResource(id = R.string.str_app_name),
                    titleStyle = MaterialTheme.typography.displayLarge.copy(color = Theme.colorScheme.red),
                    onFinish = {},
                    isBackButton = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "알림"
                        )
                    })
            }

            when (homeUiState.value) {
                is ConnectInfo.Available -> {
                    if (categoryList.isNotEmpty()) {
                        //나라 타입
                        itemsIndexed(items = categoryList) { index, item ->
                            val categoryItemList = item.categoryList
                            if (item.countryType == Const.OVERSEA) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = dp20, end = dp20),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(dp20)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.str_go_oversea),
                                        color = Theme.colorScheme.blue,
                                        style = textStyle
                                    )
                                    Divider(
                                        modifier = Modifier.weight(1f, fill = false),
                                        thickness = 5.dp,
                                        color = Theme.colorScheme.pureGray
                                    )
                                }
                            }

                            if (!categoryItemList.isNullOrEmpty()) {
                                val itemListSize = categoryItemList.size
                                val column =
                                    if (itemListSize >= row) {
                                        (itemListSize / row) + (if (itemListSize % row > 0) 1 else 0)
                                    } else 1

                                val gridHeight = (column * CategoryItemHeight.value) + (column * 2)

                                // 카테고리 뷰
                                LazyVerticalGrid(
                                    modifier = Modifier
                                        .height(gridHeight.dp)
                                        .padding(start = dp20, end = dp20),
                                    columns = GridCells.Fixed(count = row),
                                    verticalArrangement = Arrangement.spacedBy(2.dp),
                                    userScrollEnabled = false
                                ) {
                                    itemsIndexed(items = categoryItemList) { index, item ->
                                        CategoryItemWidget(item)
                                    }
                                }
                            }
                        }
                    }

                    item {
                        //배너
                        if (bannerList.isNotEmpty()) {
                            BannerWidget(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                bannerList = bannerList
                            )
                        }

                        //쿠폰, 도전뽑기, 이벤트
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = dp10, horizontal = dp10),
                            horizontalArrangement = Arrangement.spacedBy(dp5),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            EventItemWidget(
                                modifier = Modifier.weight(1f),
                                title = stringResource(id = R.string.str_coupon)
                            )
                            EventItemWidget(
                                modifier = Modifier.weight(1f),
                                title = stringResource(id = R.string.str_advance_select)
                            )
                            EventItemWidget(
                                modifier = Modifier.weight(1f),
                                title = stringResource(id = R.string.str_event)
                            )
                        }

                        //지금 신규가입하면~

                        recentStay.value.stayList?.let {
                            if (it.isNotEmpty()) {
                                HotelVerticalWidget(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    stayData = StayData(
                                        Const.RECENT_HOTEL,
                                        "최근 본 상품",
                                        recentStay.value.stayList
                                    )
                                )
                            }
                        }
                    }
                    if (stayList.isNotEmpty()) {
                        items(items = stayList) { stayData ->
                            HotelVerticalWidget(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                stayData = stayData,
                                recentStay = recentStay
                            )
                        }
                    }

                    item {
                        Divider(color = Theme.colorScheme.pureBlue, thickness = dp8)
                    }
                    if (overSeaCityList.isNotEmpty()) {
                        item {
                            Column(Modifier.background(color = Theme.colorScheme.pureGray)) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = dp15,
                                        end = 15.dp,
                                        top = dp20
                                    ),
                                    text = buildAnnotatedString {
                                        withStyle(
                                            SpanStyle(
                                                fontFamily = GMarketSansFamily,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 14.sp,
                                                color = Theme.colorScheme.blue
                                            )
                                        ) {
                                            val str =
                                                stringResource(id = R.string.str_over_sea_row_price)
                                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                                append(str.substring(0, 7))
                                            }
                                            append(str.substring(7))
                                        }
                                    },
                                    color = Theme.colorScheme.blue,
                                    style = textStyle
                                )
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(dp10)
                                ) {
                                    itemsIndexed(items = overSeaCityList) { index, item ->
                                        if (index == 0) Spacer(Modifier.width(dp15))
                                        Column(
                                            modifier = Modifier
                                                .clickable {

                                                }
                                                .padding(
                                                    start = dp5,
                                                    end = dp5,
                                                    top = dp20,
                                                    bottom = dp20
                                                ),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            OverSeaWidget(item)
                                        }
                                        if (index == overSeaCityList.lastIndex) Spacer(
                                            Modifier.width(
                                                dp15
                                            )
                                        )

                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dp20))
                        CompanyInfoWidget()
                        Spacer(modifier = Modifier.height(dp30))
                    }
                }
                is ConnectInfo.Loading -> {}
                else -> {
                    //Error
                }
            }
        }

        if (isShowHeader && viewModel.allCategoryList.isNotEmpty()) {
            StickyHeaderWidget(
                modifier = Modifier
                    .fillMaxWidth(),
                viewModel.allCategoryList,
                onClickMore = {
                    viewModel.isShowFullHeader.value = true
                })
        }

        if (isShowFullHeader.value) {
            FullHeaderWidget(
                categoryItem = viewModel.allCategoryList,
                onClickClose = {
                    viewModel.isShowFullHeader.value = false
                })
        }
    }
}
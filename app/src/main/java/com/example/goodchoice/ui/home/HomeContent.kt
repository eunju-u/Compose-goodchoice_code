package com.example.goodchoice.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.ConnectInfo
import com.example.goodchoice.ui.alarm.AlarmActivity
import com.example.goodchoice.ui.components.CategoryItemWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.home.model.RefreshData
import com.example.goodchoice.ui.home.widget.*
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val refreshDataList = listOf(
    RefreshData(R.drawable.img_bed, "잠깐 쉬고 싶을 때"),
    RefreshData(R.drawable.img_drink, "몰디브 한잔이 땡길 땐"),
    RefreshData(R.drawable.img_hotel, "나만의 휴가가 필요할 때 ")
)

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun HomeContent(
    modifier: Modifier = Modifier, viewModel: MainViewModel
) {
    val context = LocalContext.current
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val homeData = viewModel.homeData.collectAsStateWithLifecycle().value
    val categoryList = homeData.categoryList ?: emptyList()
    val bannerList = homeData.bannerList ?: emptyList()
    val stayList = homeData.stayList ?: emptyList()
    val recentDbData = viewModel.recentDbData
    val overSeaCityList = homeData.overSeaCityList ?: emptyList()
    val overseaSpecialList = homeData.overseaSpecialList ?: emptyList()
    val row = 4

    val isShowFullHeader = viewModel.isShowFullHeader
    val lazyColumnListState = rememberLazyListState()
    val textStyle = MaterialTheme.typography.displayMedium
    // header 노출하기 위한 플래그
    val isShowHeader by remember {
        derivedStateOf {
            lazyColumnListState.firstVisibleItemIndex > 1
        }
    }

    val scope = rememberCoroutineScope()
    var isRefresh by remember { mutableStateOf(false) }

    var refreshDataIndex by remember { mutableStateOf(0) }
    val refreshData = refreshDataList[refreshDataIndex]

    LaunchedEffect(isRefresh) {
        while (isRefresh) {
            delay(150)
            refreshDataIndex = (refreshDataIndex + 1) % refreshDataList.size
        }
    }

    Box(modifier = modifier) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefresh), onRefresh = {
            scope.launch {
                isRefresh = homeUiState.value is ConnectInfo.Available
                viewModel.requestHomeData(isRefresh)
                isRefresh = false
            }
        }, indicator = { s, trigger -> {} }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyColumnListState
            ) {
                item {
                    if (isRefresh) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = dp15, bottom = dp15),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier.padding(end = dp10),
                                painter = painterResource(id = refreshData.icon),
                                contentDescription = "image"
                            )
                            Text(text = refreshData.text)
                        }
                    }
                    HomeTopBarWidget(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dp30, bottom = dp30),
                        isTitleText = false,
                        titleIcon = {
                            Image(
                                modifier = Modifier.size(dp120),
                                painter = painterResource(id = R.drawable.img_goodchoice),
                                contentDescription = stringResource(id = R.string.str_app_name)
                            )
                        },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .padding(end = dp20)
                                    .clickable {
                                        context.startActivity(
                                            Intent(context, AlarmActivity::class.java)
                                        )
                                    },
                                painter = painterResource(id = R.drawable.ic_notification),
                                tint = Theme.colorScheme.darkGray,
                                contentDescription = "알림"
                            )
                        })
                }

                // 스플래쉬에서 진입한 경우 서버 조회 끝날 때까지 화면 모든 내용 보이지 않도록 한다.
                // 해당 플래그 제거하면 서버 조회 후 받아오는 데이터 제외한 "쿠폰,도전뽑기,이벤트" 뷰나 사업자 정보 뷰가 노훛된다.
                if (!viewModel.firstSplash) {
                    if (categoryList.isNotEmpty()) {
                        //나라 타입
                        itemsIndexed(items = categoryList) { index, item ->
                            val categoryItemList = item.categoryList
                            if (item.countryType == Const.OVERSEA) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = dp40, end = dp40, top = dp5, bottom = dp5),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(dp25)
                                ) {
                                    TextWidget(
                                        text = stringResource(id = R.string.str_go_oversea),
                                        color = Theme.colorScheme.blue,
                                        style = textStyle.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Divider(
                                        modifier = Modifier.weight(1f),
                                        thickness = dp2,
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

                                val gridHeight =
                                    (column * CategoryItemHeight.value) + (column * 2)

                                // 카테고리 뷰
                                LazyVerticalGrid(
                                    modifier = Modifier
                                        .height(gridHeight.dp)
                                        .padding(start = dp20, end = dp20),
                                    columns = GridCells.Fixed(count = row),
                                    verticalArrangement = Arrangement.spacedBy(dp2),
                                    userScrollEnabled = false
                                ) {
                                    itemsIndexed(items = categoryItemList) { index, item ->
                                        CategoryItemWidget(
                                            painter = painterResource(
                                                id = item.icon ?: R.drawable.bg_white
                                            ),
                                            name = item.name ?: "",
                                            height = CategoryItemHeight,
                                            onItemClick = {}
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(dp5))
                            }
                        }
                    }

                    item {
                        //배너
                        if (bannerList.isNotEmpty()) {
                            BannerWidget(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dp120)
                                    .background(color = Theme.colorScheme.pureGray),
                                bannerList = bannerList
                            )
                        }

                        //쿠폰, 도전뽑기, 이벤트
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = dp15, horizontal = dp10),
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

                        if (recentDbData.isNotEmpty()) {
                            HotelVerticalWidget(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                type = Const.RECENT_HOTEL,
                                title = "최근 본 상품",
                                stayList = recentDbData,
                            )
                            Divider(color = Theme.colorScheme.pureBlue, thickness = dp8)
                        }

                        if (stayList.isNotEmpty()) {
                            stayList.forEach { stayData ->
                                HotelVerticalWidget(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    type = stayData.type ?: "",
                                    title = stayData.title ?: "",
                                    stayList = stayData.stayList ?: listOf()
                                )
                            }
                            Spacer(modifier = Modifier.height(dp5))
                        }

                        Divider(color = Theme.colorScheme.pureBlue, thickness = dp8)

                        if (overSeaCityList.isNotEmpty()) {
                            Column(Modifier.background(color = Theme.colorScheme.pureGray)) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = dp15,
                                        end = dp15,
                                        top = dp20
                                    ),
                                    text = StringUtil.setTextColor(
                                        originText = stringResource(id = R.string.str_over_sea_row_price),
                                        targetText = "최저가 숙소"
                                    ),
                                    style = MaterialTheme.typography.displaySmall.copy(
                                        fontWeight = FontWeight.Bold
                                    )
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

                        if (overseaSpecialList.isNotEmpty()) {
                            HotelVerticalWidget(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                type = Const.OVERSEA_SPECIAL,
                                title = "해외 항공 + 숙소 이번 주 특가",
                                stayList = overseaSpecialList
                            )
                        }

                        Spacer(modifier = Modifier.height(dp20))
                        CompanyInfoWidget()
                        Spacer(modifier = Modifier.height(dp30))
                    }
                }
            }
        }
    }


    if (isShowHeader && viewModel.allCategoryList.isNotEmpty()) {
        Box {
            StickyHeaderWidget(
                modifier = Modifier
                    .fillMaxWidth(),
                viewModel.allCategoryList,
                onClickMore = {
                    viewModel.isShowFullHeader.value = true
                })
        }
    }

    if (isShowFullHeader.value) {
        Box {
            FullHeaderWidget(
                categoryItem = viewModel.allCategoryList,
                onClickClose = {
                    viewModel.isShowFullHeader.value = false
                })
        }
    }
}
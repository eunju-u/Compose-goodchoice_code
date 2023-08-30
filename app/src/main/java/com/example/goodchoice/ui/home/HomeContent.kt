package com.example.goodchoice.ui.home

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.goodchoice.Const
import com.example.goodchoice.ui.theme.CategoryItemHeight
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R
import com.example.goodchoice.data.*
import com.example.goodchoice.ui.components.TopBarWidget
import com.example.goodchoice.ui.main.MainViewModel

@Composable
fun HomeContent(
    modifier: Modifier, homeData: HomeData, viewModel: MainViewModel
) {
    val context = LocalContext.current
    val categoryList = homeData.categoryList ?: emptyList()
    val bannerList = homeData.bannerList ?: emptyList()
    val stayList = homeData.stayList ?: emptyList()
    val row = 4

    var isShowFullHeader by remember { mutableStateOf(false) }
    val lazyColumnListState = rememberLazyListState()

    // header 노출하기 위한 플래그
    val isShowHeader by remember {
        derivedStateOf {
            lazyColumnListState.firstVisibleItemIndex > 1
        }
    }

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnListState
        ) {
            item {
                TopBarWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    title = "여기 어때",
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

            if (categoryList.isNotEmpty()) {
                //나라 타입
                itemsIndexed(items = categoryList) { index, item ->
                    val categoryItemList = item.categoryList
                    if (item.countryType == Const.OVERSEA) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "해외 여행 갈때",
                                color = Theme.colorScheme.blue,
                                style = MaterialTheme.typography.displayMedium
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
                                .padding(start = 20.dp, end = 20.dp),
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
                BannerWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    bannerList = bannerList
                )
            }

            if (stayList.isNotEmpty()) {
                items(items = stayList) { stayData ->
                    HotelVerticalWidget(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp), stayData = stayData
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        if (isShowHeader && viewModel.allCategoryList.isNotEmpty()) {
            StickyHeaderWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.colorScheme.white)
                    .padding(20.dp),
                viewModel.allCategoryList,
                onClickMore = {
                    isShowFullHeader = true
                })
        }

        if (isShowFullHeader) {
            FullHeaderWidget(
                categoryItem = viewModel.allCategoryList,
                onClickClose = {
                    isShowFullHeader = false
                })
        }
    }
}
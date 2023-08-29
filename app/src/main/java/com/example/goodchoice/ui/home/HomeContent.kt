package com.example.goodchoice.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.Const
import com.example.goodchoice.ui.theme.CategoryItemHeight
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R
import com.example.goodchoice.data.*
import com.example.goodchoice.ui.components.TopBarWidget

@Composable
fun HomeContent(modifier: Modifier, homeData: HomeData) {
    val context = LocalContext.current
    val categoryList = homeData.categoryList ?: emptyList()
    val bannerList = homeData.bannerList ?: emptyList()
    val stayList = homeData.stayList ?: emptyList()
    val row = 4

    LazyColumn(modifier = modifier) {
        item {
            TopBarWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp),
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
                Log.d("eunju", "categoryItem : $item")

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

                    Log.d("eunju", "column :: $column")
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
    }
}

@Preview
@Composable
fun previewHomeContent() {
    HomeContent(
        modifier = Modifier.fillMaxSize(), homeData =
        HomeData(
            categoryList = listOf(
                CategoryData(
                    Const.KOREA,
                    listOf(
                        CategoryItem(0, "프리미엄블랙", R.drawable.ic_airplane),
                        CategoryItem(1, "모텔", R.drawable.ic_airplane),
                        CategoryItem(2, "호텔*리조트", R.drawable.ic_airplane),
                        CategoryItem(3, "펜션*풀빌라", R.drawable.ic_airplane),
                        CategoryItem(4, "홈&빌라", R.drawable.ic_airplane),
                        CategoryItem(5, "캠핑*글램핑", R.drawable.ic_airplane),
                        CategoryItem(6, "게하*한옥", R.drawable.ic_airplane),
                        CategoryItem(7, "공간대여", R.drawable.ic_airplane),
                        CategoryItem(8, "국내 항공", R.drawable.ic_airplane),
                        CategoryItem(9, "렌터카", R.drawable.ic_airplane),
                        CategoryItem(10, "레저*티켓", R.drawable.ic_airplane),
                        CategoryItem(11, "맛집", R.drawable.ic_airplane)
                    )
                ),
                CategoryData(
                    Const.OVERSEA,
                    listOf(
                        CategoryItem(0, "해외 항공", R.drawable.ic_airplane),
                        CategoryItem(1, "해외 숙소", R.drawable.ic_airplane),
                        CategoryItem(2, "항공+숙소", R.drawable.ic_airplane)
                    )
                )
            ),
            bannerList = listOf(
                BannerData(R.drawable.shape_purple),
                BannerData(R.drawable.shape_yellow),
                BannerData(R.drawable.shape_teal)
            ),
            stayList = listOf(
                StayData(
                    "오늘 체크인 호텔 특가",
                    listOf(
                        StayItem(
                            label = "호텔.리조트",
                            name = "양양 더 앤 리조트 호텔&스파",
                            star = "8.7",
                            commentCount = 906,
                            location = "양양군.주문진터미널",
                            discountPer = 50,
                            defaultPrice = 330000,
                            discountPrice = 166470
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 프밀리 풀빌라",
                            star = "9.4",
                            commentCount = 885,
                            location = "거제시.와현해수욕장",
                            discountPer = 6,
                            defaultPrice = 59000,
                            discountPrice = 55460
                        )
                    )
                ),
                StayData(
                    "오늘 HOT 인기 펜션",
                    listOf(
                        StayItem(
                            label = "호텔.리조트",
                            name = "양양 더 앤 리조트 호텔&스파",
                            star = "8.7",
                            commentCount = 906,
                            location = "양양군.주문진터미널",
                            discountPer = 50,
                            defaultPrice = 330000,
                            discountPrice = 166470
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 프밀리 풀빌라",
                            star = "9.4",
                            commentCount = 885,
                            location = "거제시.와현해수욕장",
                            discountPer = 6,
                            defaultPrice = 59000,
                            discountPrice = 55460
                        )
                    )
                )
            )
        )
    )
}
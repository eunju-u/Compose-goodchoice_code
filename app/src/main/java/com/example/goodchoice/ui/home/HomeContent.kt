package com.example.goodchoice.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.goodchoice.Const
import com.example.goodchoice.data.HomeData
import com.example.goodchoice.ui.theme.CategoryItemHeight
import com.example.goodchoice.ui.theme.Theme

@Composable
fun HomeContent(modifier: Modifier, homeData: HomeData) {
    val context = LocalContext.current
    val categoryList = homeData.categoryList ?: emptyList()
    val bannerList = homeData.bannerList ?: emptyList()
    val row = 5

    LazyColumn(modifier = modifier) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .wrapContentSize(Alignment.Center),
                text = "여기 어때",
                color = Theme.colorScheme.red,
                style = MaterialTheme.typography.displayLarge
            )
        }

        if (categoryList.isNotEmpty()) {
            //나라 타입
            itemsIndexed(items = categoryList) { index, item ->
                val categoryItemList = item.categoryList
                if (item.countryType == Const.OVERSEA) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(start = 30.dp, end = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "해외 여행 갈때",
                            color = Theme.colorScheme.blue,
                            style = MaterialTheme.typography.displayLarge
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
                            (itemListSize / row) + if (itemListSize % row > 0) 1 else 0
                        } else 1
                    val gridHeight = (column * CategoryItemHeight.value) + (column * 15)

                    // 카테고리 뷰
                    LazyVerticalGrid(
                        modifier = Modifier
                            .height(gridHeight.dp)
                            .padding(start = 40.dp, end = 40.dp),
                        columns = GridCells.Fixed(count = 5),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
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
    }

}
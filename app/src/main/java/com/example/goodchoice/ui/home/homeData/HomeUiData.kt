package com.example.goodchoice.ui.home.homeData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.goodchoice.api.data.BannerData
import com.example.goodchoice.api.data.CategoryData
import com.example.goodchoice.api.data.OverSeaCityItem
import com.example.goodchoice.api.data.StayData
import java.util.LinkedList

/**
 * 서버에서 받아온 데이터 UI 에서 편하게 쓰도록 가공함.
 */
data class HomeUiData(
    var categoryList: LinkedList<CategoryData> = LinkedList(),
    var bannerList: LinkedList<BannerData> = LinkedList(),
    var stayList: LinkedList<StayData> = LinkedList(),
    var recentStay: MutableState<HomeRecentData> = mutableStateOf(HomeRecentData()),
    var overSeaCityList: LinkedList<OverSeaCityItem> = LinkedList()
)
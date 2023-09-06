package com.example.goodchoice.ui.home.homeData

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.goodchoice.data.BannerData
import com.example.goodchoice.data.CategoryData
import com.example.goodchoice.data.OverSeaCityItem
import com.example.goodchoice.data.StayData
import java.util.LinkedList

data class HomeUiData(
    var categoryList: LinkedList<CategoryData> = LinkedList(),
    var bannerList: LinkedList<BannerData> = LinkedList(),
    var stayList: LinkedList<StayData> = LinkedList(),
    var recentStayList: SnapshotStateList<StayData> = mutableStateListOf(),
    var overSeaCityList: LinkedList<OverSeaCityItem> = LinkedList()
)
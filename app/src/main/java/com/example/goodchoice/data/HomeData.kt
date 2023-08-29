package com.example.goodchoice.data

data class HomeData(
    val categoryList: List<CategoryData>? = listOf(),
    val bannerList: List<BannerData>? = listOf(),
    val stayList: List<StayData>? = listOf(),
)
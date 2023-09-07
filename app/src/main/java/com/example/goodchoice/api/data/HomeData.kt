package com.example.goodchoice.api.data

data class HomeData(
    val categoryList: List<CategoryData>? = emptyList(), //카테고리
    val bannerList: List<BannerData>? = emptyList(), //배너
    val stayList: List<StayData>? = emptyList(), //숙소
    val overSeaCityList: List<OverSeaCityItem>? = emptyList() //해외도시
)
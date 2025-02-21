package com.example.home.data.remote.dto

data class HomeDto(
    val categoryList: List<CategoryDto>? = emptyList(), //카테고리
    val bannerList: List<BannerDto>? = emptyList(), //배너
    val stayList: List<StayDto>? = emptyList(), //숙소
    val overSeaCityList: List<OverSeaCityItemDto>? = emptyList(), //해외도시
    val overseaSpecialList: List<OverseaSpecialItemDto>? = emptyList() //해외 숙소 특가
)
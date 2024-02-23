package com.example.goodchoice.data.dto

// 주변 화면에서 사용되는 필터, 추천순, 숙소유형 ...
data class AroundFilterData(
    val type: String? = "",
    val text: String? = "",
    val filterList: List<AroundFilterItem>? = listOf()
)

data class AroundFilterItem(
    val type: String? = "",
    val code: String? = "",
    val text: String? = ""
)
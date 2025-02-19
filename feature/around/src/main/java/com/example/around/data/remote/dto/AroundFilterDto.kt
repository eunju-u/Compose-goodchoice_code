package com.example.around.data.remote.dto

// 주변 화면에서 사용되는 필터, 추천순, 숙소유형 ...
data class AroundFilterDto(
    val type: String? = "",
    val text: String? = "",
    val filterList: List<AroundFilterItemDto>? = listOf()
)

data class AroundFilterItemDto(
    val type: String? = "",
    val text: String? = ""
)
package com.example.filter.domain.model

data class FilterData(
    val code: String? = "",
    val title: String? = "",
    val list: List<FilterListData>? = listOf()
)

data class FilterListData(
    val code: String? = "",
    val title: String? = "",
    val list: List<FilterItem>? = listOf()
)
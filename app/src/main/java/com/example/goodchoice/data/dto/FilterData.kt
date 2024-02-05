package com.example.goodchoice.data.dto

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
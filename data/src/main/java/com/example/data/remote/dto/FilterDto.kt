package com.example.data.remote.dto

data class FilterDto(
    val code: String? = "",
    val title: String? = "",
    val list: List<FilterListDto>? = listOf()
)

data class FilterListDto(
    val code: String? = "",
    val title: String? = "",
    val list: List<FilterItemDto>? = listOf()
)
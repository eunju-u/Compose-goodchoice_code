package com.example.data.remote.dto

data class CategoryDto(
    val countryType: String? = "",
    val categoryList: List<CategoryItemDto>? = listOf(),
)
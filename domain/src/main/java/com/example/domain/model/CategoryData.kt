package com.example.domain.model

data class CategoryData(
    val countryType: String? = "",
    val categoryList: List<CategoryItem>? = listOf(),
)
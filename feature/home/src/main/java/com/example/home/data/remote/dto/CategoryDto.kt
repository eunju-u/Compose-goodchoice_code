package com.example.home.data.remote.dto

import com.example.data.remote.dto.CategoryItemDto

data class CategoryDto(
    val countryType: String? = "",
    val categoryList: List<CategoryItemDto>? = listOf(),
)
package com.example.home.data.remote.dto

import com.example.data.remote.dto.StayItemDto

data class StayDto(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItemDto>? = emptyList(),
)
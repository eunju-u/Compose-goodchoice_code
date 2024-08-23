package com.example.data.remote.dto

data class StayDto(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItemDto>? = emptyList(),
)
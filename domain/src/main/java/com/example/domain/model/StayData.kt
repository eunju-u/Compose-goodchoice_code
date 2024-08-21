package com.example.domain.model

data class StayData(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItem>? = emptyList(),
)
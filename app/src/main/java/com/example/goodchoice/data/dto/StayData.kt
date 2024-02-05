package com.example.goodchoice.data.dto

data class StayData(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItem>? = emptyList(),
)
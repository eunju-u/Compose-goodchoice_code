package com.example.goodchoice.api.data

data class StayData(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItem>? = emptyList(),
)
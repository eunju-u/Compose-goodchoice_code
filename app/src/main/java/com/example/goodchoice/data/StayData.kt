package com.example.goodchoice.data

data class StayData(
    val type: Int? = 0,
    val title: String? = "",
    val stayList: List<StayItem>? = emptyList(),
)
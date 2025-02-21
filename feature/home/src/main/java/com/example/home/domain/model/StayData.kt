package com.example.home.domain.model

import com.example.domain.model.StayItem

data class StayData(
    val type: String? = "",
    val title: String? = "",
    val stayList: List<StayItem>? = emptyList(),
)
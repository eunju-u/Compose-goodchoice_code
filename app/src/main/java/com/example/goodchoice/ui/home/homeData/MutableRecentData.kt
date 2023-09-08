package com.example.goodchoice.ui.home.homeData

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.goodchoice.api.data.StayItem

data class MutableRecentData(
    val type: Int? = 0,
    val title: String? = "",
    val stayList: SnapshotStateList<StayItem>? = mutableStateListOf(),
)
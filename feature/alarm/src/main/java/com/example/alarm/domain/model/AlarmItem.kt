package com.example.alarm.domain.model

data class AlarmItem(
    val alarmIdx: Int? = 0,
    val title: String? = "",
    val content: String? = "",
    val date: String? = ""
)
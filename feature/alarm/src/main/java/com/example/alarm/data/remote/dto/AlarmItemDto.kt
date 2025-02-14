package com.example.alarm.data.remote.dto

data class AlarmItemDto(
    val alarmIdx: Int? = 0,
    val title: String? = "",
    val content: String? = "",
    val date: String? = ""
)
package com.example.data.mapper

import com.example.data.remote.dto.AlarmItemDto
import com.example.domain.model.AlarmItem

fun AlarmItemDto.generateData(): AlarmItem {
    val item = this@generateData

    return AlarmItem(
        alarmIdx = item.alarmIdx,
        title = item.title,
        content = item.content,
        date = item.date,
    )
}
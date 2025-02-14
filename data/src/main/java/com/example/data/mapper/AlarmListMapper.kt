package com.example.data.mapper

import com.example.data.remote.dto.AlarmItemDto
import com.example.domain.model.AlarmItem

fun AlarmItemDto.generateAlarmItem(): AlarmItem {
    val item = this@generateAlarmItem

    return AlarmItem(
        alarmIdx = item.alarmIdx,
        title = item.title,
        content = item.content,
        date = item.date,
    )
}
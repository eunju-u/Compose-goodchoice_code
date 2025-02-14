package com.example.alarm.data.mapper

import com.example.alarm.data.remote.dto.AlarmItemDto
import com.example.alarm.domain.model.AlarmItem

fun AlarmItemDto.generateAlarmItem(): AlarmItem {
    val item = this@generateAlarmItem

    return AlarmItem(
        alarmIdx = item.alarmIdx,
        title = item.title,
        content = item.content,
        date = item.date,
    )
}
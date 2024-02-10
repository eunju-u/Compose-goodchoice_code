package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.AlarmItem

interface AlarmRepository {
    suspend fun getAlarmData():List<AlarmItem>
}
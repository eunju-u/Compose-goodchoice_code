package com.example.domain.repository

import com.example.domain.model.AlarmItem

interface AlarmRepository {
    suspend fun getAlarmData():List<AlarmItem>
}
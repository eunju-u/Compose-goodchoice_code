package com.example.alarm.domain.repository

import com.example.alarm.domain.model.AlarmItem

interface AlarmRepository {
    suspend fun getAlarmData():List<AlarmItem>
}
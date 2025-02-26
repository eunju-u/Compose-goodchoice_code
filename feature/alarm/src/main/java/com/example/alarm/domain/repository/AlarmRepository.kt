package com.example.alarm.domain.repository

import com.example.alarm.domain.model.AlarmItem
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    fun getAlarmData(): Flow<List<AlarmItem>>
}
package com.example.alarm.data.repository

import com.example.alarm.data.mapper.generateAlarmItem
import com.example.alarm.domain.model.AlarmItem
import com.example.alarm.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val dataSource: com.example.alarm.data.dataSource.AlarmDataSource
) : AlarmRepository {
    override suspend fun getAlarmData(): List<AlarmItem> {
        return dataSource.getAlarmData().map {
            it.generateAlarmItem()
        }
    }
}
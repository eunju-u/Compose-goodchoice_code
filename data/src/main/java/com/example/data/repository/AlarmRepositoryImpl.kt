package com.example.data.repository

import com.example.data.dataSource.AlarmDataSource
import com.example.data.mapper.generateData
import com.example.domain.model.AlarmItem
import com.example.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val dataSource: AlarmDataSource
) : AlarmRepository {
    override suspend fun getAlarmData(): List<AlarmItem> {
        return dataSource.getAlarmData().map {
            it.generateData()
        }
    }
}
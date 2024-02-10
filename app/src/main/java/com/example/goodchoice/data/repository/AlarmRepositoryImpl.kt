package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.AlarmDataSource
import com.example.goodchoice.data.dto.AlarmItem
import com.example.goodchoice.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val dataSource: AlarmDataSource
) : AlarmRepository {
    override suspend fun getAlarmData(): List<AlarmItem> {
        return dataSource.getAlarmData()
    }
}
package com.example.alarm.data.repository

import com.example.alarm.data.dataSource.AlarmDataSource
import com.example.alarm.data.mapper.generateAlarmItem
import com.example.alarm.domain.model.AlarmItem
import com.example.alarm.domain.repository.AlarmRepository
import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: AlarmDataSource
) : AlarmRepository {
    override fun getAlarmData(): Flow<List<AlarmItem>> = flow {
        emit(dataSource.getAlarmData().map {
            it.generateAlarmItem()
        })
    }.flowOn(ioDispatcher)
}
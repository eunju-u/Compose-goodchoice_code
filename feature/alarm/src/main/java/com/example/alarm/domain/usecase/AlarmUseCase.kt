package com.example.alarm.domain.usecase

import com.example.alarm.domain.model.AlarmItem
import com.example.alarm.domain.repository.AlarmRepository
import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getAlarmData(): Flow<List<AlarmItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getAlarmData())
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }
}
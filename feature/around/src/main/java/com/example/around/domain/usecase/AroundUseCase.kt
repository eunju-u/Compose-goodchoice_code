package com.example.around.domain.usecase

import com.example.around.domain.model.AroundFilterData
import com.example.around.domain.repository.AroundRepository
import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AroundUseCase @Inject constructor(
    private val repository: AroundRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    fun getSleepData(): Flow<List<AroundFilterData>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getSleepData())
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }

    fun getRentalData(): Flow<List<AroundFilterData>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getRentalData())
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }
}
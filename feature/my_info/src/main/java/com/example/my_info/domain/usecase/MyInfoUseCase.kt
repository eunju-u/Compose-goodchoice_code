package com.example.my_info.domain.usecase

import com.example.common.utils.DeviceUtil
import com.example.common.exception.NetworkUnavailableException
import com.example.my_info.domain.model.MyInfoData
import com.example.my_info.domain.repository.MyInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyInfoUseCase @Inject constructor(
    private val repository: MyInfoRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    fun getMyInfoData(): Flow<MyInfoData> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getMyInfoData())
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }
}
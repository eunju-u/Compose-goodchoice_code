package com.example.domain.usecase

import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import com.example.domain.model.StayItem
import com.example.domain.repository.RecentSeenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentUseCase @Inject constructor(
    private val repository: RecentSeenRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    fun getList(): Flow<List<StayItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getList())
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }

    suspend fun deleteList() {
        return try {
            withContext(Dispatchers.IO) {
                repository.deleteList()
            }
        } catch (e: Exception) {
            //TODO 예외처리
        }
    }
}
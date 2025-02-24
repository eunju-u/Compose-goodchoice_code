package com.example.domain.usecase

import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import com.example.domain.model.StayItem
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getLikeData(): Flow<List<StayItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getLikeData())
    }.catch { e ->
        throw e
    }

    suspend fun hasLikeData(stayItemId: String): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                repository.hasLikeData(stayItemId)
            }
        } catch (e: Exception) {
            //TODO 예외처리
            false
        }
    }

    suspend fun insertLikeData(stayItemId: String) {
        return try {
            withContext(Dispatchers.IO) {
                repository.insertLikeData(stayItemId)
            }
        } catch (e: Exception) {
            //TODO 예외처리
        }
    }

    suspend fun deleteLikeData(stayId: String) {
        return try {
            withContext(Dispatchers.IO) {
                repository.deleteLikeData(stayId)
            }
        } catch (e: Exception) {
            //TODO 예외처리
        }
    }
}
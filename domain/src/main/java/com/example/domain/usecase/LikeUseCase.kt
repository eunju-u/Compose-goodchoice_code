package com.example.domain.usecase

import com.example.domain.model.StayItem
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository,
) {
    suspend fun getLikeData(): List<StayItem> {
        return try {
            withContext(Dispatchers.IO) {
                delay(100)
                repository.getLikeData()
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
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
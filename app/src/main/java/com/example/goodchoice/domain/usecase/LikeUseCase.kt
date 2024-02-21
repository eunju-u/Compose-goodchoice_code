package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

//TODO eunju : IO 두번 호출하는거 수정 필요,,
class LikeUseCase @Inject constructor(
    private val repository: LikeRepository,
) {
    suspend fun getLikeData(): List<StayItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getLikeData()
                }
                delay(100)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun hasLikeData(stayItemId: String): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.hasLikeData(stayItemId)
                }
                val data = resultDeferred.await()
                data
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

    suspend fun deleteLikeData(stayId: String): Boolean {
        return try {
            repository.deleteLikeData(stayId)
        } catch (e: Exception) {
            //TODO 예외처리
            false
        }
    }
}
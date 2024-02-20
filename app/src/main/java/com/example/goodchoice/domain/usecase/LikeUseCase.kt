package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

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
}
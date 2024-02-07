package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.StayDetailData
import com.example.goodchoice.domain.repository.StayDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StayDetailUseCase @Inject constructor(private val repository: StayDetailRepository) {
    suspend fun getDetailData(stayItemId: String): StayDetailData {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getDetailData(stayItemId)
                }
                delay(600)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            StayDetailData()
        }
    }
}
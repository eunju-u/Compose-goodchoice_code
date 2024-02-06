package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.AroundFilterData
import com.example.goodchoice.domain.repository.AroundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AroundUseCase @Inject constructor(
    private val repository: AroundRepository,
) {
    suspend fun getSleepData(): List<AroundFilterData> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getSleepData()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRentalData(): List<AroundFilterData> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getRentalData()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }
}
package com.example.domain.usecase

import com.example.domain.model.HomeData
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend fun getHomeData(): HomeData {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getHomeData()
                }
                delay(100)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            HomeData()
        }
    }
}
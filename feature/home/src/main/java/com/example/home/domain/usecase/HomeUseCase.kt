package com.example.home.domain.usecase

import com.example.home.domain.model.HomeData
import com.example.home.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend fun getHomeData(): HomeData {
        return try {
            delay(100)
            repository.getHomeData()
        } catch (e: Exception) {
            //TODO 예외처리
            HomeData()
        }
    }
}
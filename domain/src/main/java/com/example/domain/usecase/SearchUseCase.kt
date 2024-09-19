package com.example.domain.usecase

import com.example.domain.model.FilterItem
import com.example.domain.model.RecommendAreaData
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend fun getKoreaRankData(): List<FilterItem> {
        return try {
            delay(100)
            repository.getKoreaRankData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRecommendWordData(): List<FilterItem> {
        return try {
            repository.getRecommendWordData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getAreaData(): List<RecommendAreaData> {
        return try {
            repository.getAreaData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }
}
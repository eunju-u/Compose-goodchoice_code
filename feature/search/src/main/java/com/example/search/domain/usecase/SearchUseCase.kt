package com.example.search.domain.usecase

import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend fun getKoreaRankData(): List<SearchItem> {
        return try {
            delay(100)
            repository.getKoreaRankData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRecommendWordData(): List<SearchItem> {
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
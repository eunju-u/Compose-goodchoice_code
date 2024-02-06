package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.data.dto.RecommendAreaData
import com.example.goodchoice.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend fun getKoreaRankData(): List<FilterItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getKoreaRankData()
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

    suspend fun getRecommendWordData(): List<FilterItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getRecommendWordData()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getAreaData(): List<RecommendAreaData> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getAreaData()
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
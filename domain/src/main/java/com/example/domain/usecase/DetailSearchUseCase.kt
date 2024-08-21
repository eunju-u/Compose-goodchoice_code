package com.example.domain.usecase

import com.example.domain.model.FilterItem
import com.example.domain.model.KoreaSearchData
import com.example.domain.repository.DetailSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailSearchUseCase @Inject constructor(
    private val repository: DetailSearchRepository,
) {
    suspend fun getSearchData(): List<KoreaSearchData> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getSearchData()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRankData(): List<FilterItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getRankData()
                }
                delay (200)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

}
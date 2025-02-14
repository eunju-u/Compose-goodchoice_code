package com.example.domain.usecase

import com.example.domain.model.KoreaSearchData
import com.example.domain.model.SearchItem
import com.example.domain.repository.DetailSearchRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class DetailSearchUseCase @Inject constructor(
    private val repository: DetailSearchRepository,
) {
    suspend fun getSearchData(): List<KoreaSearchData> {
        return try {
            repository.getSearchData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRankData(): List<SearchItem> {
        return try {
            delay (200)
            repository.getRankData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

}
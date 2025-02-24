package com.example.search.domain.repository

import com.example.domain.model.KoreaSearchData
import com.example.search.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface DetailSearchRepository {
    suspend fun getSearchData(): Flow<List<KoreaSearchData>>

    suspend fun getRankData(): Flow<List<SearchItem>>
}
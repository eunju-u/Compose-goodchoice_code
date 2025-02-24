package com.example.search.domain.repository

import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getKoreaRankData(): Flow<List<SearchItem>>
    fun getRecommendWordData(): Flow<List<SearchItem>>
    fun getAreaData(): Flow<List<RecommendAreaData>>
}
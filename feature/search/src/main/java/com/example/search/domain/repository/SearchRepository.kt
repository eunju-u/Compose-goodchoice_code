package com.example.search.domain.repository

import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem

interface SearchRepository {
    suspend fun getKoreaRankData(): List<SearchItem>
    suspend fun getRecommendWordData(): List<SearchItem>
    suspend fun getAreaData(): List<RecommendAreaData>
}
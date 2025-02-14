package com.example.domain.repository

import com.example.domain.model.RecommendAreaData
import com.example.domain.model.SearchItem

interface SearchRepository {
    suspend fun getKoreaRankData(): List<SearchItem>
    suspend fun getRecommendWordData(): List<SearchItem>
    suspend fun getAreaData(): List<RecommendAreaData>
}
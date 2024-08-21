package com.example.domain.repository

import com.example.domain.model.FilterItem
import com.example.domain.model.RecommendAreaData

interface SearchRepository {
    suspend fun getKoreaRankData(): List<FilterItem>
    suspend fun getRecommendWordData(): List<FilterItem>
    suspend fun getAreaData(): List<RecommendAreaData>
}
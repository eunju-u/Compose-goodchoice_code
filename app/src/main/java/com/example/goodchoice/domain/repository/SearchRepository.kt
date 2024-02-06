package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.data.dto.RecommendAreaData

interface SearchRepository {
    suspend fun getKoreaRankData(): List<FilterItem>
    suspend fun getRecommendWordData(): List<FilterItem>
    suspend fun getAreaData(): List<RecommendAreaData>
}
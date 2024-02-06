package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.SearchDataSource
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.data.dto.RecommendAreaData
import com.example.goodchoice.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchDataSource
) : SearchRepository {

    override suspend fun getKoreaRankData(): List<FilterItem> {
        return dataSource.getKoreaRankData()
    }

    override suspend fun getRecommendWordData(): List<FilterItem> {
        return dataSource.getRecommendWordData()
    }

    override suspend fun getAreaData(): List<RecommendAreaData> {
        return dataSource.getAreaData()
    }
}
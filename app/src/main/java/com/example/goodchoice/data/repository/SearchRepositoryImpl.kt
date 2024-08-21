package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.SearchDataSource
import com.example.domain.model.FilterItem
import com.example.domain.model.RecommendAreaData
import com.example.domain.repository.SearchRepository
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
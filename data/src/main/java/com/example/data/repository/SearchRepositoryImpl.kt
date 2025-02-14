package com.example.data.repository

import com.example.data.dataSource.SearchDataSource
import com.example.data.mapper.generateFilterItem
import com.example.data.mapper.generateRecommendAreaData
import com.example.domain.model.FilterItem
import com.example.domain.model.RecommendAreaData
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchDataSource
) : SearchRepository {

    override suspend fun getKoreaRankData(): List<FilterItem> {
        return dataSource.getKoreaRankData().map { it.generateFilterItem() }
    }

    override suspend fun getRecommendWordData(): List<FilterItem> {
        return dataSource.getRecommendWordData().map { it.generateFilterItem() }
    }

    override suspend fun getAreaData(): List<RecommendAreaData> {
        return dataSource.getAreaData().map { it.generateRecommendAreaData() }
    }
}
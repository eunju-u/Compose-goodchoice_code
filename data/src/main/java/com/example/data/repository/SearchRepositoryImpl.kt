package com.example.data.repository

import com.example.data.dataSource.SearchDataSource
import com.example.data.mapper.generateRecommendAreaData
import com.example.data.mapper.generateSearchItem
import com.example.domain.model.RecommendAreaData
import com.example.domain.model.SearchItem
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchDataSource
) : SearchRepository {

    override suspend fun getKoreaRankData(): List<SearchItem> {
        return dataSource.getKoreaRankData().map { it.generateSearchItem() }
    }

    override suspend fun getRecommendWordData(): List<SearchItem> {
        return dataSource.getRecommendWordData().map { it.generateSearchItem() }
    }

    override suspend fun getAreaData(): List<RecommendAreaData> {
        return dataSource.getAreaData().map { it.generateRecommendAreaData() }
    }
}
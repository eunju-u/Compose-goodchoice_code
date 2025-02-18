package com.example.search.data.repository

import com.example.search.data.mapper.generateRecommendAreaData
import com.example.search.data.mapper.generateSearchItem
import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.SearchRepository
import com.example.search.data.dataSource.SearchDataSource
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
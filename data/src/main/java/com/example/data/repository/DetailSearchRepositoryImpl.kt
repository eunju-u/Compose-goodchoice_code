package com.example.data.repository

import com.example.data.dataSource.DetailSearchDataSource
import com.example.data.mapper.generateData
import com.example.domain.model.FilterItem
import com.example.domain.model.KoreaSearchData
import com.example.domain.repository.DetailSearchRepository
import javax.inject.Inject

class DetailSearchRepositoryImpl @Inject constructor(
    private val dataSource: DetailSearchDataSource
) : DetailSearchRepository {

    override suspend fun getSearchData(): List<KoreaSearchData> {
        return dataSource.getSearchData().map { it.generateData() }
    }

    override suspend fun getRankData(): List<FilterItem> {
        return dataSource.getRankData().map { it.generateData() }
    }
}
package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.DetailSearchDataSource
import com.example.domain.model.FilterItem
import com.example.domain.repository.DetailSearchRepository
import com.example.domain.model.KoreaSearchData
import javax.inject.Inject

class DetailSearchRepositoryImpl @Inject constructor(
    private val dataSource: DetailSearchDataSource
) : DetailSearchRepository {

    override suspend fun getSearchData(): List<KoreaSearchData> {
        return dataSource.getSearchData()
    }

    override suspend fun getRankData(): List<FilterItem> {
        return dataSource.getRankData()
    }
}
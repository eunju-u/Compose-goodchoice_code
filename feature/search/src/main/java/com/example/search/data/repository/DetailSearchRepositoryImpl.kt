package com.example.search.data.repository

import com.example.search.data.dataSource.DetailSearchDataSource
import com.example.search.data.mapper.generateKoreaSearchData
import com.example.search.data.mapper.generateSearchItem
import com.example.domain.model.KoreaSearchData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.DetailSearchRepository
import javax.inject.Inject

class DetailSearchRepositoryImpl @Inject constructor(
    private val dataSource: DetailSearchDataSource
) : DetailSearchRepository {

    override suspend fun getSearchData(): List<KoreaSearchData> {
        return dataSource.getSearchData().map { it.generateKoreaSearchData() }
    }

    override suspend fun getRankData(): List<SearchItem> {
        return dataSource.getRankData().map { it.generateSearchItem() }
    }
}
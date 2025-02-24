package com.example.search.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.search.data.dataSource.DetailSearchDataSource
import com.example.search.data.mapper.generateKoreaSearchData
import com.example.search.data.mapper.generateSearchItem
import com.example.domain.model.KoreaSearchData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.DetailSearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailSearchRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: DetailSearchDataSource
) : DetailSearchRepository {

    override suspend fun getSearchData(): Flow<List<KoreaSearchData>> = flow {
        emit(dataSource.getSearchData().map { it.generateKoreaSearchData() })
    }.flowOn(ioDispatcher)

    override suspend fun getRankData(): Flow<List<SearchItem>> = flow {
        emit(dataSource.getRankData().map { it.generateSearchItem() })
    }.flowOn(ioDispatcher)
}
package com.example.search.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.search.data.mapper.generateRecommendAreaData
import com.example.search.data.mapper.generateSearchItem
import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.SearchRepository
import com.example.search.data.dataSource.SearchDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: SearchDataSource
) : SearchRepository {
    override fun getKoreaRankData(): Flow<List<SearchItem>> = flow {
        emit(dataSource.getKoreaRankData().map { it.generateSearchItem() })
    }.flowOn(ioDispatcher)

    override fun getRecommendWordData(): Flow<List<SearchItem>> = flow {
        emit(dataSource.getRecommendWordData().map { it.generateSearchItem() })
    }.flowOn(ioDispatcher)

    override fun getAreaData(): Flow<List<RecommendAreaData>> = flow {
        emit(dataSource.getAreaData().map { it.generateRecommendAreaData() })
    }.flowOn(ioDispatcher)

}
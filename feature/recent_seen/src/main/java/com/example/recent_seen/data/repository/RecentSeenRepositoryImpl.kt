package com.example.recent_seen.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.data.mapper.generateStayItem
import com.example.domain.model.StayItem
import com.example.domain.repository.RecentSeenRepository
import com.example.recent_seen.data.dataSource.RecentSeenDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecentSeenRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: RecentSeenDataSource
) : RecentSeenRepository {

    override suspend fun getList(): Flow<List<StayItem>> = flow {
        emit(dataSource.getList().map {
            it.generateStayItem()
        })
    }.flowOn(ioDispatcher)

    override suspend fun deleteList() {
        return dataSource.deleteList()
    }
}
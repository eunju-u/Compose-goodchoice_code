package com.example.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.data.dataSource.LikeDataSource
import com.example.data.mapper.generateStayItem
import com.example.domain.model.StayItem
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: LikeDataSource
) : LikeRepository {
    override suspend fun getLikeData(): Flow<List<StayItem>> = flow {
        emit(dataSource.getLikeData().map { it.generateStayItem() })
    }.flowOn(ioDispatcher)

    override suspend fun hasLikeData(stayItemId: String): Boolean {
        return dataSource.hasLikeData(stayItemId)
    }

    override suspend fun insertLikeData(stayItemId: String) {
        dataSource.insertLikeData(com.example.database.like.LikeDbItem(stayItemId))
    }

    override suspend fun deleteLikeData(stayId: String) {
        return dataSource.deleteLikeData(stayId)
    }
}

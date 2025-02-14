package com.example.data.repository

import com.example.data.dataSource.LikeDataSource
import com.example.data.mapper.generateStayItem
import com.example.domain.model.StayItem
import com.example.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dataSource: LikeDataSource
) : LikeRepository {
    override suspend fun getLikeData(): List<StayItem> {
        return dataSource.getLikeData().map { it.generateStayItem() }
    }

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

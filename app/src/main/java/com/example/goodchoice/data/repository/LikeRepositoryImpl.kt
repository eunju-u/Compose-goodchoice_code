package com.example.goodchoice.data.repository

import com.example.domain.model.StayItem
import com.example.goodchoice.data.dataSource.LikeDataSource
import com.example.domain.repository.LikeRepository
import com.example.database.like.LikeDbItem
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dataSource: LikeDataSource
) : LikeRepository {
    override suspend fun getLikeData(): List<StayItem> {
        return dataSource.getLikeData()
    }

    override suspend fun hasLikeData(stayItemId: String): Boolean {
        return dataSource.hasLikeData(stayItemId)
    }

    override suspend fun insertLikeData(stayItemId: String) {
        dataSource.insertLikeData(LikeDbItem(stayItemId))
    }

    override suspend fun deleteLikeData(stayId: String) {
        return dataSource.deleteLikeData(stayId)
    }
}

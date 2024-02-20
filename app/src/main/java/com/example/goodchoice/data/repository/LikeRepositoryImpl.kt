package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.LikeDataSource
import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl@Inject constructor(
    private val dataSource: LikeDataSource
) : LikeRepository {
    override suspend fun getLikeData(): List<StayItem> {
        return dataSource.getLikeData()
    }
}

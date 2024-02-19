package com.example.goodchoice.data.repository

import android.content.Context
import com.example.goodchoice.data.dataSource.StayDetailDataSource
import com.example.goodchoice.data.dto.StayDetailData
import com.example.goodchoice.db.like.LikeDbItem
import com.example.goodchoice.domain.repository.StayDetailRepository
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): StayDetailData {
        return dataSource.getDetailData(stayItemId)
    }

    override suspend fun hasLikeData(context: Context, stayItemId: String): Boolean {
        return dataSource.hasLikeData(context, stayItemId)
    }

    override suspend fun insertLikeData(context: Context, stayItemId: String) {
        dataSource.insertLikeData(context, LikeDbItem(stayItemId))
    }
}
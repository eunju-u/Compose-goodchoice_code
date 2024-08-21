package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.StayDetailDataSource
import com.example.domain.model.StayDetailData
import com.example.domain.repository.StayDetailRepository
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): StayDetailData {
        return dataSource.getDetailData(stayItemId)
    }
}
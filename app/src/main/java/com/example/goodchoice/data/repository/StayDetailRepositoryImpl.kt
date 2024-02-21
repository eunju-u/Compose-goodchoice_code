package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.StayDetailDataSource
import com.example.goodchoice.data.dto.StayDetailData
import com.example.goodchoice.domain.repository.StayDetailRepository
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): StayDetailData {
        return dataSource.getDetailData(stayItemId)
    }
}
package com.example.data.repository

import com.example.data.dataSource.StayDetailDataSource
import com.example.data.mapper.generateStayDetailData
import com.example.domain.model.StayDetailData
import com.example.domain.repository.StayDetailRepository
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): StayDetailData {
        return dataSource.getDetailData(stayItemId).generateStayDetailData()
    }
}
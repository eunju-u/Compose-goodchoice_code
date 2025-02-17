package com.example.stay_detail.data.repository

import com.example.stay_detail.data.mapper.generateStayDetailData
import com.example.stay_detail.domain.model.StayDetailData
import com.example.stay_detail.domain.repository.StayDetailRepository
import com.example.stay_detail.data.dataSource.StayDetailDataSource
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): StayDetailData {
        return dataSource.getDetailData(stayItemId).generateStayDetailData()
    }
}
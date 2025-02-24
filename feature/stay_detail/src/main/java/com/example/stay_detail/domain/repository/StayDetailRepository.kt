package com.example.stay_detail.domain.repository

import com.example.stay_detail.domain.model.StayDetailData
import kotlinx.coroutines.flow.Flow

interface StayDetailRepository {
    suspend fun getDetailData(stayItemId: String) : Flow<StayDetailData>
}
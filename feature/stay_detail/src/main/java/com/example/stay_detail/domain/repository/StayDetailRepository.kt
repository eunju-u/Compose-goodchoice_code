package com.example.stay_detail.domain.repository

import com.example.stay_detail.domain.model.StayDetailData

interface StayDetailRepository {
    suspend fun getDetailData(stayItemId: String) : StayDetailData
}
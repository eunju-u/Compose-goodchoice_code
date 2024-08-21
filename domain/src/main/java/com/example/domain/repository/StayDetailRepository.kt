package com.example.domain.repository

import com.example.domain.model.StayDetailData

interface StayDetailRepository {
    suspend fun getDetailData(stayItemId: String) : StayDetailData
}
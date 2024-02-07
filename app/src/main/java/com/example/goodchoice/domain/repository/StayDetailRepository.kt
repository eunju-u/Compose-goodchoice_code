package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.StayDetailData

interface StayDetailRepository {
    suspend fun getDetailData(stayItemId: String) : StayDetailData
}
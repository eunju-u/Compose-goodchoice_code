package com.example.goodchoice.domain.repository

import android.content.Context
import com.example.goodchoice.data.dto.StayDetailData

interface StayDetailRepository {
    suspend fun getDetailData(stayItemId: String) : StayDetailData
    suspend fun hasLikeData(context: Context, stayItemId: String): Boolean
    suspend fun insertLikeData(context: Context, stayItemId: String)
}
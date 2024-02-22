package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.StayItem

interface LikeRepository {
    suspend fun getLikeData(): List<StayItem>
    suspend fun hasLikeData(stayItemId: String): Boolean
    suspend fun insertLikeData(stayItemId: String)
    suspend fun deleteLikeData(stayId: String)
}
package com.example.domain.repository

import com.example.domain.model.StayItem

interface LikeRepository {
    suspend fun getLikeData(): List<StayItem>
    suspend fun hasLikeData(stayItemId: String): Boolean
    suspend fun insertLikeData(stayItemId: String)
    suspend fun deleteLikeData(stayId: String)
}
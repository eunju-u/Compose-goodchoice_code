package com.example.domain.repository

import com.example.domain.model.StayItem
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    suspend fun getLikeData(): Flow<List<StayItem>>
    suspend fun hasLikeData(stayItemId: String): Boolean
    suspend fun insertLikeData(stayItemId: String)
    suspend fun deleteLikeData(stayId: String)
}
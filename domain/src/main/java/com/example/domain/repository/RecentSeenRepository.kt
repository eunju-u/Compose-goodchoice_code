package com.example.domain.repository

import com.example.domain.model.StayItem
import kotlinx.coroutines.flow.Flow

interface RecentSeenRepository {
    suspend fun getList(): Flow<List<StayItem>>
    suspend fun deleteList()
}
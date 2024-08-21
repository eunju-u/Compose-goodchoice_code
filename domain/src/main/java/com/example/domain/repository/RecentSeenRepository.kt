package com.example.domain.repository

import com.example.domain.model.StayItem

interface RecentSeenRepository {
    suspend fun getList(): List<StayItem>
    suspend fun deleteList()
}
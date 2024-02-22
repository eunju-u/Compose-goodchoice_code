package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.StayItem

interface RecentSeenRepository {
    suspend fun getList(): List<StayItem>
    suspend fun deleteList()
}
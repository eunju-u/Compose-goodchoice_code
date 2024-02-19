package com.example.goodchoice.domain.repository

import com.example.goodchoice.db.recent.RecentDbItem

interface RecentSeenRepository {
    suspend fun getList(): List<RecentDbItem>
    suspend fun deleteList()
}
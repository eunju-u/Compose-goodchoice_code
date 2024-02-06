package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.RecentSeenDataSource
import com.example.goodchoice.db.RecentDbItem
import com.example.goodchoice.domain.repository.RecentSeenRepository
import javax.inject.Inject

class RecentSeenRepositoryImpl @Inject constructor(
    private val dataSource: RecentSeenDataSource
) : RecentSeenRepository {

    override suspend fun getList(): List<RecentDbItem> {
        return dataSource.getList()
    }

    override suspend fun deleteList() {
        return dataSource.deleteList()
    }
}
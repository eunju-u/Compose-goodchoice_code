package com.example.recent_seen.data.repository

import com.example.data.mapper.generateStayItem
import com.example.domain.model.StayItem
import com.example.domain.repository.RecentSeenRepository
import com.example.recent_seen.data.dataSource.RecentSeenDataSource
import javax.inject.Inject

class RecentSeenRepositoryImpl @Inject constructor(
    private val dataSource: RecentSeenDataSource
) : RecentSeenRepository {

    override suspend fun getList(): List<StayItem> {
        return dataSource.getList().map {
            it.generateStayItem()
        }
    }

    override suspend fun deleteList() {
        return dataSource.deleteList()
    }
}
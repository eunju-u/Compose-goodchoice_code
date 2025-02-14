package com.example.data.repository

import com.example.data.dataSource.RecentSeenDataSource
import com.example.data.mapper.generateStayItem
import com.example.domain.model.StayItem
import com.example.domain.repository.RecentSeenRepository
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
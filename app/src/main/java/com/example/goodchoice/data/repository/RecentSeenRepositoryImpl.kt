package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.RecentSeenDataSource
import com.example.goodchoice.data.dto.StayItem
import com.example.goodchoice.domain.repository.RecentSeenRepository
import com.example.goodchoice.mapper.generateData
import javax.inject.Inject

class RecentSeenRepositoryImpl @Inject constructor(
    private val dataSource: RecentSeenDataSource
) : RecentSeenRepository {

    override suspend fun getList(): List<StayItem> {
        return dataSource.getList().map {
            it.generateData()
        }
    }

    override suspend fun deleteList() {
        return dataSource.deleteList()
    }
}
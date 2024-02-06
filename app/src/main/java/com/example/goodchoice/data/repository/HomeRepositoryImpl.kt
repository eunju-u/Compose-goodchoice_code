package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.HomeDataSource
import com.example.goodchoice.data.dto.HomeData
import com.example.goodchoice.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getHomeData(): HomeData {
        return dataSource.getHomeData()
    }
}
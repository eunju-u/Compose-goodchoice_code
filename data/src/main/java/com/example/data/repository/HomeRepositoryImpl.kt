package com.example.data.repository

import com.example.data.dataSource.HomeDataSource
import com.example.data.mapper.generateHomeData
import com.example.domain.model.HomeData
import com.example.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getHomeData(): HomeData {
        return dataSource.getHomeData().generateHomeData()
    }
}
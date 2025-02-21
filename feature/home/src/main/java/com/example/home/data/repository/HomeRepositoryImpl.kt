package com.example.home.data.repository

import com.example.home.data.mapper.generateHomeData
import com.example.home.domain.model.HomeData
import com.example.home.domain.repository.HomeRepository
import com.example.home.data.dataSource.HomeDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getHomeData(): HomeData {
        return dataSource.getHomeData().generateHomeData()
    }
}
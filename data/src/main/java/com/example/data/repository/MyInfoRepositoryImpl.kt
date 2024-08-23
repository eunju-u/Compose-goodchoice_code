package com.example.data.repository

import com.example.data.dataSource.MyInfoDataSource
import com.example.data.mapper.generateData
import com.example.domain.model.MyInfoData
import com.example.domain.repository.MyInfoRepository
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {

    override suspend fun getMyInfoData(): MyInfoData {
        return dataSource.getMyInfoDate().generateData()
    }
}
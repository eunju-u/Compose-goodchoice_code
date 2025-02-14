package com.example.data.repository

import com.example.data.dataSource.MyInfoDataSource
import com.example.data.mapper.generateMyInfoData
import com.example.domain.model.MyInfoData
import com.example.domain.repository.MyInfoRepository
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {

    override suspend fun getMyInfoData(): MyInfoData {
        return dataSource.getMyInfoDate().generateMyInfoData()
    }
}
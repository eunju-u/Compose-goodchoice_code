package com.example.my_info.data.repository

import com.example.my_info.domain.model.MyInfoData
import com.example.my_info.domain.repository.MyInfoRepository
import com.example.my_info.data.dataSource.MyInfoDataSource
import com.example.my_info.data.mapper.generateMyInfoData
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {

    override suspend fun getMyInfoData(): MyInfoData {
        return dataSource.getMyInfoDate().generateMyInfoData()
    }
}
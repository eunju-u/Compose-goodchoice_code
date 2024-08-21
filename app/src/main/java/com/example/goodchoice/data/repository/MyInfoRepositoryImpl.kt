package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.MyInfoDataSource
import com.example.domain.model.MyInfoData
import com.example.domain.repository.MyInfoRepository
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {

    override suspend fun getMyInfoData(): MyInfoData {
        return dataSource.getMyInfoDate()
    }
}
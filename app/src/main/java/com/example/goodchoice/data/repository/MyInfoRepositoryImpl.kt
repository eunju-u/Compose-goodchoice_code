package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.MyInfoDataSource
import com.example.goodchoice.data.dto.MyInfoData
import com.example.goodchoice.domain.repository.MyInfoRepository
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {

    override suspend fun getMyInfoData(): MyInfoData {
        return dataSource.getMyInfoDate()
    }
}
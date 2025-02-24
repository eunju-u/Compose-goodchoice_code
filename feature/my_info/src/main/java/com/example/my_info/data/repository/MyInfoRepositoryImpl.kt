package com.example.my_info.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.my_info.domain.model.MyInfoData
import com.example.my_info.domain.repository.MyInfoRepository
import com.example.my_info.data.dataSource.MyInfoDataSource
import com.example.my_info.data.mapper.generateMyInfoData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: MyInfoDataSource
) : MyInfoRepository {
    override fun getMyInfoData(): Flow<MyInfoData> = flow {
        emit(dataSource.getMyInfoDate().generateMyInfoData())
    }.flowOn(ioDispatcher)

}
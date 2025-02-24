package com.example.stay_detail.data.repository

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.stay_detail.data.mapper.generateStayDetailData
import com.example.stay_detail.domain.model.StayDetailData
import com.example.stay_detail.domain.repository.StayDetailRepository
import com.example.stay_detail.data.dataSource.StayDetailDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StayDetailRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: StayDetailDataSource
) : StayDetailRepository {
    override suspend fun getDetailData(stayItemId: String): Flow<StayDetailData> = flow {
        emit(dataSource.getDetailData(stayItemId).generateStayDetailData())
    }.flowOn(ioDispatcher)
}
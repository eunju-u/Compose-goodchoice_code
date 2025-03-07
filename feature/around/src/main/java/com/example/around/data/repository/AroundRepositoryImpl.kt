package com.example.around.data.repository

import com.example.around.data.dataSource.AroundDataSource
import com.example.around.data.mapper.generateAroundFilterData
import com.example.around.domain.model.AroundFilterData
import com.example.around.domain.repository.AroundRepository
import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AroundRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: AroundDataSource
) : AroundRepository {

    override fun getSleepData(): Flow<List<AroundFilterData>> = flow {
        emit(dataSource.getSleepData().map {
            it.generateAroundFilterData()
        })
    }.flowOn(ioDispatcher)

    override fun getRentalData(): Flow<List<AroundFilterData>> = flow {
        emit(dataSource.getRentalData().map {
            it.generateAroundFilterData()
        })
    }.flowOn(ioDispatcher)
}
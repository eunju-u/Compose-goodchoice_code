package com.example.around.data.repository

import com.example.around.data.dataSource.AroundDataSource
import com.example.around.data.mapper.generateAroundFilterData
import com.example.around.domain.model.AroundFilterData
import com.example.around.domain.repository.AroundRepository
import javax.inject.Inject

class AroundRepositoryImpl @Inject constructor(
    private val dataSource: AroundDataSource
) : AroundRepository {

    override suspend fun getSleepData(): List<AroundFilterData> {
        return dataSource.getSleepData().map {
            it.generateAroundFilterData()
        }
    }

    override suspend fun getRentalData(): List<AroundFilterData> {
        return dataSource.getRentalData().map {
            it.generateAroundFilterData()
        }
    }
}
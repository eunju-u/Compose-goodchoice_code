package com.example.data.repository

import com.example.data.dataSource.AroundDataSource
import com.example.data.mapper.generateAroundFilterData
import com.example.domain.model.AroundFilterData
import com.example.domain.repository.AroundRepository
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
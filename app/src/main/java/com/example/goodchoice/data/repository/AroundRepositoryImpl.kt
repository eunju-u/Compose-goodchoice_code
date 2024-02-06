package com.example.goodchoice.data.repository

import com.example.goodchoice.data.dataSource.AroundDataSource
import com.example.goodchoice.data.dto.AroundFilterData
import com.example.goodchoice.domain.repository.AroundRepository
import javax.inject.Inject

class AroundRepositoryImpl @Inject constructor(
    private val dataSource: AroundDataSource
) : AroundRepository {

    override suspend fun getSleepData(): List<AroundFilterData> {
        return dataSource.getSleepData()
    }

    override suspend fun getRentalData(): List<AroundFilterData> {
        return dataSource.getRentalData()
    }
}
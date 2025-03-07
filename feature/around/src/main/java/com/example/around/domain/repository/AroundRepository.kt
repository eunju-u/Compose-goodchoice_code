package com.example.around.domain.repository

import com.example.around.domain.model.AroundFilterData
import kotlinx.coroutines.flow.Flow

interface AroundRepository {
    fun getSleepData(): Flow<List<AroundFilterData>>
    fun getRentalData(): Flow<List<AroundFilterData>>
}
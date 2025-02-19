package com.example.around.domain.repository

import com.example.around.domain.model.AroundFilterData

interface AroundRepository {
    suspend fun getSleepData(): List<AroundFilterData>
    suspend fun getRentalData(): List<AroundFilterData>
}
package com.example.domain.repository

import com.example.domain.model.AroundFilterData

interface AroundRepository {
    suspend fun getSleepData(): List<AroundFilterData>
    suspend fun getRentalData(): List<AroundFilterData>
}
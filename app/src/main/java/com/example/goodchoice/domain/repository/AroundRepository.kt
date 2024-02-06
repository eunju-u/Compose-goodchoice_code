package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.AroundFilterData

interface AroundRepository {
    suspend fun getSleepData(): List<AroundFilterData>
    suspend fun getRentalData(): List<AroundFilterData>
}
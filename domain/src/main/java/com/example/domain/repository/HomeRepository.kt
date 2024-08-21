package com.example.domain.repository

import com.example.domain.model.HomeData

interface HomeRepository {
    suspend fun getHomeData(): HomeData
}
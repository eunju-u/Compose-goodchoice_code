package com.example.home.domain.repository

import com.example.home.domain.model.HomeData

interface HomeRepository {
    suspend fun getHomeData(): HomeData
}
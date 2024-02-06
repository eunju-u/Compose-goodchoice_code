package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.HomeData

interface HomeRepository {
    suspend fun getHomeData(): HomeData
}
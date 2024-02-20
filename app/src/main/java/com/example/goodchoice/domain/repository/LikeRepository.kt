package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.StayItem

interface LikeRepository {
    suspend fun getLikeData():List<StayItem>
}
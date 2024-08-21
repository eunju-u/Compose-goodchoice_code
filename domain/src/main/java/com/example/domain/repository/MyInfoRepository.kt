package com.example.domain.repository

import com.example.domain.model.MyInfoData

interface MyInfoRepository {
    suspend fun getMyInfoData(): MyInfoData
}
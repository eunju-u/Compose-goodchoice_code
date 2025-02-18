package com.example.my_info.domain.repository

import com.example.my_info.domain.model.MyInfoData

interface MyInfoRepository {
    suspend fun getMyInfoData(): MyInfoData
}
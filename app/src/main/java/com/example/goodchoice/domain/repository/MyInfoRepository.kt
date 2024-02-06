package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.MyInfoData

interface MyInfoRepository {
    suspend fun getMyInfoData(): MyInfoData
}
package com.example.my_info.domain.repository

import com.example.my_info.domain.model.MyInfoData
import kotlinx.coroutines.flow.Flow

interface MyInfoRepository {
    fun getMyInfoData(): Flow<MyInfoData>
}
package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.FilterData
import com.example.goodchoice.data.dto.FilterItem

interface FilterRepository {
    suspend fun getTypeData(): List<FilterItem>
    suspend fun getFilterData(stayType: String): List<FilterData>
}
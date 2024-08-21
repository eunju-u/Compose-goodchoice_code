package com.example.domain.repository

import com.example.domain.model.FilterData
import com.example.domain.model.FilterItem

interface FilterRepository {
    suspend fun getTypeData(): List<FilterItem>
    suspend fun getFilterData(stayType: String): List<FilterData>
}
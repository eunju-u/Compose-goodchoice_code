package com.example.filter.domain.repository

import com.example.filter.domain.model.FilterData
import com.example.filter.domain.model.FilterItem

interface FilterRepository {
    suspend fun getTypeData(): List<FilterItem>
    suspend fun getFilterData(stayType: String): List<FilterData>
}
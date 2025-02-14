package com.example.data.repository

import com.example.data.dataSource.FilterDataSource
import com.example.data.mapper.generateFilterData
import com.example.data.mapper.generateFilterItem
import com.example.domain.model.FilterData
import com.example.domain.model.FilterItem
import com.example.domain.repository.FilterRepository
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val dataSource: FilterDataSource
) : FilterRepository {
    override suspend fun getTypeData(): List<FilterItem> {
        return dataSource.getTypeData().map { it.generateFilterItem() }
    }

    override suspend fun getFilterData(stayType: String): List<FilterData> {
        return dataSource.getFilterData(stayType).map { it.generateFilterData() }
    }
}
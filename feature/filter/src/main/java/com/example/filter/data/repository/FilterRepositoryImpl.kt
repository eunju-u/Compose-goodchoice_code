package com.example.filter.data.repository

import com.example.filter.data.mapper.generateFilterData
import com.example.filter.data.mapper.generateFilterItem
import com.example.filter.domain.model.FilterData
import com.example.filter.domain.model.FilterItem
import com.example.filter.domain.repository.FilterRepository
import com.example.filter.data.dataSource.FilterDataSource
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
package com.example.filter.domain.usecase

import com.example.filter.domain.model.FilterData
import com.example.filter.domain.model.FilterItem
import com.example.filter.domain.repository.FilterRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class FilterUseCase @Inject constructor(
    private val repository: FilterRepository
) {

    suspend fun getTypeData(): List<FilterItem> {
        return try {
            delay(100)
            repository.getTypeData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getFilterData(stayType: String): List<FilterData> {
        return try {
            delay(200)
            repository.getFilterData(stayType)
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }
}
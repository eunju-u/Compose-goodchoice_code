package com.example.domain.usecase

import com.example.domain.model.FilterData
import com.example.domain.model.FilterItem
import com.example.domain.repository.FilterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilterUseCase @Inject constructor(
    private val repository: FilterRepository
) {

    suspend fun getTypeData(): List<FilterItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getTypeData()
                }
                delay(100)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getFilterData(stayType: String): List<FilterData> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getFilterData(stayType)
                }
                delay(200)
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }
}
package com.example.domain.usecase

import com.example.domain.model.StayItem
import com.example.domain.repository.RecentSeenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentUseCase @Inject constructor(
    private val repository: RecentSeenRepository,
) {
    suspend fun getList(): List<StayItem> {
        return try {
            withContext(Dispatchers.IO) {
                repository.getList()
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun deleteList() {
        return try {
            withContext(Dispatchers.IO) {
                repository.deleteList()
            }
        } catch (e: Exception) {
            //TODO 예외처리
        }
    }
}
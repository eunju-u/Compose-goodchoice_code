package com.example.goodchoice.domain.usecase

import com.example.goodchoice.db.RecentDbItem
import com.example.goodchoice.domain.repository.RecentSeenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentUseCase @Inject constructor(
    private val repository: RecentSeenRepository,
) {
    suspend fun getList(): List<RecentDbItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getList()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun deleteList() {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.deleteList()
                }
                resultDeferred.await()
            }
        } catch (e: Exception) {
            //TODO 예외처리
        }
    }
}
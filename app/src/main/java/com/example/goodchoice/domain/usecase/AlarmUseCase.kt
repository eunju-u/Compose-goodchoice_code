package com.example.goodchoice.domain.usecase

import com.example.goodchoice.data.dto.AlarmItem
import com.example.goodchoice.domain.repository.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    suspend fun getAlarmData(): List<AlarmItem> {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getAlarmData()
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
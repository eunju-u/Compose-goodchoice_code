package com.example.goodchoice.domain.usecase

import com.example.goodchoice.domain.repository.AlarmRepository
import com.example.goodchoice.ui.alarm.AlarmConnectInfo
import com.example.goodchoice.utils.DeviceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    suspend fun getAlarmData(): AlarmConnectInfo {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getAlarmData()
                }
                delay(200)

                if (!DeviceUtil.isNetworkAvailable()) throw Exception("network is not connected")

                val data = resultDeferred.await()
                AlarmConnectInfo.Available(data)
            }
        } catch (e: Exception) {
            AlarmConnectInfo.Error(e.message)
        }
    }
}
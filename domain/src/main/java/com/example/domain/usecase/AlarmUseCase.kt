package com.example.domain.usecase

import com.example.common.utils.DeviceUtil
import com.example.domain.info.AlarmConnectInfo
import com.example.domain.repository.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getAlarmData(): AlarmConnectInfo {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getAlarmData()
                }
                delay(200)

                if (!deviceUtil.isNetworkAvailable()) throw Exception("network is not connected")

                val data = resultDeferred.await()
                AlarmConnectInfo.Available(data)
            }
        } catch (e: Exception) {
            AlarmConnectInfo.Error(e.message)
        }
    }
}
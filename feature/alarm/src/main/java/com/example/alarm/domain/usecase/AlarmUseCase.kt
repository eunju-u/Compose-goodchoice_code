package com.example.alarm.domain.usecase

import com.example.alarm.domain.info.AlarmConnectInfo
import com.example.alarm.domain.repository.AlarmRepository
import com.example.common.utils.DeviceUtil
import kotlinx.coroutines.delay
import javax.inject.Inject

class AlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getAlarmData(): AlarmConnectInfo {
        return try {
            //!! async는 동시에 여러 비동기 작업을 수행할 때 유용하지만, 네트워크 호출 내부에서 IO 처리하고 여긴 suspend 함수로 동작하니까 async 필요 없다.
            delay(200)
            if (!deviceUtil.isNetworkAvailable()) throw Exception("network is not connected")
            AlarmConnectInfo.Available(repository.getAlarmData())
        } catch (e: Exception) {
            AlarmConnectInfo.Error(e.message)
        }
    }
}
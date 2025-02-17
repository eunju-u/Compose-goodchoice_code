package com.example.stay_detail.domain.usecase

import com.example.common.utils.DeviceUtil
import com.example.stay_detail.domain.info.StayDetailConnectInfo
import com.example.stay_detail.domain.repository.StayDetailRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class StayDetailUseCase @Inject constructor(private val repository: StayDetailRepository) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getDetailData(stayItemId: String): StayDetailConnectInfo {
        return try {
            //!! async는 동시에 여러 비동기 작업을 수행할 때 유용하지만, 네트워크 호출 내부에서 IO 처리하고 여긴 suspend 함수로 동작하니까 async 필요 없다.
            delay(600)
            if (!deviceUtil.isNetworkAvailable()) throw Exception("network is not connected")
            StayDetailConnectInfo.Available(repository.getDetailData(stayItemId))
        } catch (e: Exception) {
            StayDetailConnectInfo.Error(e.message)
        }
    }
}
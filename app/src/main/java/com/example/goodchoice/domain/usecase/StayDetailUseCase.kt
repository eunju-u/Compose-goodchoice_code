package com.example.goodchoice.domain.usecase

import com.example.goodchoice.domain.repository.StayDetailRepository
import com.example.goodchoice.ui.stayDetail.StayDetailConnectInfo
import com.example.goodchoice.utils.DeviceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StayDetailUseCase @Inject constructor(private val repository: StayDetailRepository) {
    suspend fun getDetailData(stayItemId: String): StayDetailConnectInfo {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getDetailData(stayItemId)
                }
                delay(600)

                if (!DeviceUtil.isNetworkAvailable()) throw Exception("network is not connected")

                val data = resultDeferred.await()
                StayDetailConnectInfo.Available(data)
            }
        } catch (e: Exception) {
            StayDetailConnectInfo.Error(e.message)
        }
    }
}
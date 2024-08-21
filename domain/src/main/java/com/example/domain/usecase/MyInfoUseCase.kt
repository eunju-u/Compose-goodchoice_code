package com.example.domain.usecase

import com.example.domain.model.MyInfoData
import com.example.domain.repository.MyInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyInfoUseCase @Inject constructor(
    private val repository: MyInfoRepository,
) {
    suspend fun getMyInfoData(): MyInfoData {
        return try {
            withContext(Dispatchers.IO) {
                val resultDeferred = async {
                    repository.getMyInfoData()
                }
                val data = resultDeferred.await()
                data
            }
        } catch (e: Exception) {
            //TODO 예외처리
            MyInfoData()
        }
    }
}
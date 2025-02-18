package com.example.my_info.domain.usecase

import com.example.my_info.domain.model.MyInfoData
import com.example.my_info.domain.repository.MyInfoRepository
import javax.inject.Inject

class MyInfoUseCase @Inject constructor(
    private val repository: MyInfoRepository,
) {
    suspend fun getMyInfoData(): MyInfoData {
        return try {
            repository.getMyInfoData()
        } catch (e: Exception) {
            //TODO 예외처리
            MyInfoData()
        }
    }
}
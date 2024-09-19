package com.example.domain.usecase

import com.example.domain.model.AroundFilterData
import com.example.domain.repository.AroundRepository
import javax.inject.Inject

class AroundUseCase @Inject constructor(
    private val repository: AroundRepository,
) {
    suspend fun getSleepData(): List<AroundFilterData> {
        return try {
            //!! async는 동시에 여러 비동기 작업을 수행할 때 유용하지만, 네트워크 호출 내부에서 IO 처리하고 여긴 suspend 함수로 동작하니까 async 필요 없다.
            repository.getSleepData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }

    suspend fun getRentalData(): List<AroundFilterData> {
        return try {
            repository.getRentalData()
        } catch (e: Exception) {
            //TODO 예외처리
            listOf()
        }
    }
}
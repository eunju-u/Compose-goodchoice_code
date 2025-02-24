package com.example.stay_detail.domain.usecase

import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import com.example.stay_detail.domain.model.StayDetailData
import com.example.stay_detail.domain.repository.StayDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StayDetailUseCase @Inject constructor(private val repository: StayDetailRepository) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getDetailData(stayItemId: String): Flow<StayDetailData> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        //!! emitAll은 하위 Flow에서 방출되는 모든 값을 상위 Flow로 방출
        //!! 다른 방안으로는 collect + emit 이 있다.
        // collect + emit 은 데이터를 수동으로 하나씩 방출, 추가 작업이 필요하지만, 중간에 데이터를 가공하거나 변환할 수 있음
        // emitAll은 하위 Flow를 상위로 그대로 방출, 간결하고 직관적이며, 추가 로직 없이 데이터를 위임 가능,
        // 하위 Flow를 중간에서 가공할 필요 없이 그대로 상위로 전달하고 싶을 때 사용
        emitAll(repository.getDetailData(stayItemId))
    }.catch { e ->
        throw NetworkUnavailableException("Network is not connected")
    }
}
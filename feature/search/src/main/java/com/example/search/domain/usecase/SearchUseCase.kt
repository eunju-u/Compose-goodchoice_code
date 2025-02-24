package com.example.search.domain.usecase

import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    fun getKoreaRankData(): Flow<List<SearchItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        delay(100)
        emitAll(repository.getKoreaRankData())
    }.catch { e ->
        throw e
    }

    fun getRecommendWordData(): Flow<List<SearchItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getRecommendWordData())
    }.catch { e ->
        throw e
    }

    fun getAreaData(): Flow<List<RecommendAreaData>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getAreaData())
    }.catch { e ->
        throw e
    }
}
package com.example.search.domain.usecase

import com.example.common.exception.NetworkUnavailableException
import com.example.common.utils.DeviceUtil
import com.example.domain.model.KoreaSearchData
import com.example.search.domain.model.SearchItem
import com.example.search.domain.repository.DetailSearchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailSearchUseCase @Inject constructor(
    private val repository: DetailSearchRepository,
) {
    @Inject
    lateinit var deviceUtil: DeviceUtil

    suspend fun getSearchData(): Flow<List<KoreaSearchData>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            emit(emptyList())
        } else {
            emitAll(repository.getSearchData())
        }
    }.catch {
        emit(emptyList())
    }

    suspend fun getRankData(): Flow<List<SearchItem>> = flow {
        if (!deviceUtil.isNetworkAvailable()) {
            throw NetworkUnavailableException("Network is not connected") // 사용자 정의 예외
        }
        emitAll(repository.getRankData())
    }.catch { e ->
        throw e
    }

}
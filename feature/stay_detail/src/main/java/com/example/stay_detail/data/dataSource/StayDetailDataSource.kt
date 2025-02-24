package com.example.stay_detail.data.dataSource

import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.stay_detail.data.remote.mock.S_D_1
import com.example.stay_detail.data.remote.dto.StayDetailDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StayDetailDataSource @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun getDetailData(stayItemId: String): StayDetailDto {
        return withContext(ioDispatcher) {
            var data = StayDetailDto()
            if (stayItemId == "s_1") {
                data = S_D_1
            }
            data
        }
    }
}
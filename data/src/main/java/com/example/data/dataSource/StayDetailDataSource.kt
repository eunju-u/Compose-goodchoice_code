package com.example.data.dataSource

import com.example.data.remote.dto.*
import com.example.data.remote.mock.S_D_1
import javax.inject.Inject

class StayDetailDataSource @Inject constructor() {
    fun getDetailData(stayItemId: String): StayDetailDto {
        var data = StayDetailDto()
        if (stayItemId == "s_1") {
            data = S_D_1
        }
        return data
    }
}
package com.example.stay_detail.data.dataSource

import com.example.stay_detail.data.remote.mock.S_D_1
import com.example.stay_detail.data.remote.dto.StayDetailDto
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
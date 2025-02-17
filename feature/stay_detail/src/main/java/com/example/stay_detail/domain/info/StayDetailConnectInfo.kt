package com.example.stay_detail.domain.info

import com.example.stay_detail.domain.model.StayDetailData

sealed interface StayDetailConnectInfo {
    object Init : StayDetailConnectInfo
    object Loading : StayDetailConnectInfo
    data class Available(val data: StayDetailData) : StayDetailConnectInfo
    data class Error(val message: String? = null) : StayDetailConnectInfo
}
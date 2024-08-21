package com.example.domain.info

import com.example.domain.model.StayDetailData

sealed interface StayDetailConnectInfo {
    object Init : StayDetailConnectInfo
    object Loading : StayDetailConnectInfo
    data class Available(val data: StayDetailData) : StayDetailConnectInfo
    data class Error(val message: String? = null) : StayDetailConnectInfo
}
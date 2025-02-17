package com.example.stay_detail.domain.model

data class PayData(
    val payType: String? = "",
    val payName: String? = "", //페이 (카카오페이, 토스페이..)
    val payInfoList: List<PayItem>? = emptyList()
)
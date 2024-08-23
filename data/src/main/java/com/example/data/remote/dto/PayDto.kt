package com.example.data.remote.dto

data class PayDto(
    val payType: String? = "",
    val payName: String? = "", //페이 (카카오페이, 토스페이..)
    val payInfoList: List<PayItemDto>? = emptyList()
)
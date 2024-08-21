package com.example.domain.info

sealed interface ConnectInfo {

    object Init : ConnectInfo
    object Loading : ConnectInfo

    data class Available(
        val data: Any? = null
    ) : ConnectInfo


    data class Error(val message: String? = null) : ConnectInfo
}
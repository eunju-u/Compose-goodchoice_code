package com.example.alarm.domain.info

import com.example.alarm.domain.model.AlarmItem

sealed interface AlarmConnectInfo {
    object Init : AlarmConnectInfo
    object Loading : AlarmConnectInfo
    data class Available(val data: List<AlarmItem>) : AlarmConnectInfo
    data class Error(val message: String? = null) : AlarmConnectInfo
}
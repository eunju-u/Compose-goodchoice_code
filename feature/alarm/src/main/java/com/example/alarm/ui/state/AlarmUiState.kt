package com.example.alarm.ui.state

import com.example.alarm.domain.model.AlarmItem

sealed interface  AlarmUiState {
    object Loading : AlarmUiState
    data class Success(val list: List<AlarmItem>) : AlarmUiState
    data class Error(val message: String) : AlarmUiState
}

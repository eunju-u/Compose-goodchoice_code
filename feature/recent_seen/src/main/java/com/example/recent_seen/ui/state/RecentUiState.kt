package com.example.recent_seen.ui.state

import com.example.domain.model.StayItem

sealed interface  RecentUiState {
    data class Success(val list: List<StayItem>) : RecentUiState
    data class Error(val message: String) : RecentUiState
    object Loading : RecentUiState
}




package com.example.domain.info

import com.example.domain.model.StayItem

sealed interface  LikeUiState {
    data class Success(val data:  List<StayItem>) : LikeUiState
    data class Error(val message: String) : LikeUiState
    object Loading : LikeUiState
}

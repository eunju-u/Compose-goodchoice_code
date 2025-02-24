package com.example.stay_detail.ui.state

import com.example.stay_detail.domain.model.StayDetailData

sealed interface  StayDetailUiState {
    data class Success(val data: StayDetailData) : StayDetailUiState
    data class Error(val message: String) : StayDetailUiState
    object Loading : StayDetailUiState
}

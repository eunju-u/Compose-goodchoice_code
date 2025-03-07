package com.example.around.ui.state

import com.example.around.domain.model.AroundFilterData

sealed interface AroundUiState {
    object Loading : AroundUiState
    data class Success(val list: List<AroundFilterData>) : AroundUiState
    data class Error(val message: String) : AroundUiState
}
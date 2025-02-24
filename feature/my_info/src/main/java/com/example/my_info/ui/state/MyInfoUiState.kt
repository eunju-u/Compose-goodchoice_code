package com.example.my_info.ui.state

import com.example.my_info.domain.model.MyInfoData

sealed interface  MyInfoUiState {
    data class Success(val list: MyInfoData) : MyInfoUiState
    data class Error(val message: String) : MyInfoUiState
    object Loading : MyInfoUiState
}

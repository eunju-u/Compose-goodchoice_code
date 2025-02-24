package com.example.search.ui.state

import com.example.search.domain.model.SearchItem

sealed interface  DetailSearchUiState {
    data class Success(val list: List<SearchItem>) : DetailSearchUiState
    data class Error(val message: String) : DetailSearchUiState
    object Loading : DetailSearchUiState
}

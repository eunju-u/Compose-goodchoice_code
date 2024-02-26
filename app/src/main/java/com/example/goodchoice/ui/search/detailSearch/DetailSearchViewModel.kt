package com.example.goodchoice.ui.search.detailSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.domain.usecase.DetailSearchUseCase
import com.example.goodchoice.ui.search.data.KoreaSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSearchViewModel @Inject constructor(
    private val useCase: DetailSearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailSearchConnectInfo>(DetailSearchConnectInfo.Init)
    val uiState: MutableStateFlow<DetailSearchConnectInfo>
        get() = _uiState

    private val _searchData = MutableStateFlow<List<KoreaSearchData>>(listOf())
    val searchData = _searchData.asStateFlow()

    fun requestSearchUiData() = viewModelScope.launch {
        _uiState.value = DetailSearchConnectInfo.Loading
        val list = useCase.getRankData()
        _uiState.value = DetailSearchConnectInfo.Available(list)
    }

    fun requestResultSearchData(newKey: String = "") = viewModelScope.launch {
        _searchData.value = if (newKey.isNotEmpty()) {
            useCase.getSearchData().filter { it.nameToKey.contains(newKey) }
        } else {
            emptyList()
        }
    }
}

sealed interface DetailSearchConnectInfo {
    object Init : DetailSearchConnectInfo
    object Loading : DetailSearchConnectInfo
    data class Available(val data: List<FilterItem>) : DetailSearchConnectInfo
    data class Error(val message: String? = null) : DetailSearchConnectInfo
}
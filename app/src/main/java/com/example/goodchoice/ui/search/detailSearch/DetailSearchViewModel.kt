package com.example.goodchoice.ui.search.detailSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.domain.usecase.DetailSearchUseCase
import com.example.goodchoice.ui.search.data.KoreaSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailSearchViewModel @Inject constructor(
    private val useCase: DetailSearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailSearchConnectInfo>(DetailSearchConnectInfo.Init)
    val uiState: MutableStateFlow<DetailSearchConnectInfo> = _uiState

    private val _searchData = MutableStateFlow<List<KoreaSearchData>>(listOf())
    val searchData = _searchData

    private val _keyWord = MutableStateFlow("")
    val keyWord: MutableStateFlow<String> = _keyWord

    init {
        viewModelScope.launch {
            _keyWord.debounce(200) // 200ms 대기 후 조회 시작 (서버 조회 자주하지 않도록)
                .filter { it.isNotBlank() }
                .flatMapLatest { newWord ->
                    requestResultSearchData(newWord)
                }
                .collect { _searchData.value = it }
        }
    }

    fun requestSearchUiData() = viewModelScope.launch {
        _uiState.value = DetailSearchConnectInfo.Loading
        val list = useCase.getRankData()
        _uiState.value = DetailSearchConnectInfo.Available(list)
    }

    private fun requestResultSearchData(newKey: String = "") = flow {
        val result = if (newKey.isNotEmpty()) {
            useCase.getSearchData().filter { it.nameToKey.contains(newKey) }
        } else {
            emptyList()
        }
        emit(result)
    }

    fun onWordChanged(newWord: String) {
        _keyWord.value = newWord
    }
}

sealed interface DetailSearchConnectInfo {
    object Init : DetailSearchConnectInfo
    object Loading : DetailSearchConnectInfo
    data class Available(val data: List<FilterItem>) : DetailSearchConnectInfo
    data class Error(val message: String? = null) : DetailSearchConnectInfo
}
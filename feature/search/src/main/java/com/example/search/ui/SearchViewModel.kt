package com.example.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.intent.CommonIntent
import com.example.search.domain.usecase.SearchUseCase
import com.example.search.ui.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _searchState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Loading)
    val searchState: StateFlow<SearchUiState> get() = _searchState

    private val _intent = MutableSharedFlow<CommonIntent>()
    private val intent: SharedFlow<CommonIntent> get() = _intent

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.distinctUntilChanged() // 동일한 Intent 무시
            intent.collect { myInfoIntent ->
                when (myInfoIntent) {
                    is CommonIntent.LoadMyInfo -> loadSearchData()
                    is CommonIntent.Retry -> loadSearchData()
                }
            }
        }
    }

    private fun loadSearchData() {
        viewModelScope.launch {
            combine(
                searchUseCase.getKoreaRankData().catch { emit(emptyList()) },  // 오류 발생 시 빈 리스트 반환
                searchUseCase.getRecommendWordData().catch { emit(emptyList()) },
                searchUseCase.getAreaData().catch { emit(emptyList()) }
            ) { koreaRank, recommendWord, area ->
                SearchUiState.Success(koreaRank, recommendWord, area)
            }.onStart {
                _searchState.value = SearchUiState.Loading
            }.catch { exception ->
                _searchState.value = SearchUiState.Error(exception.message ?: "Unknown error")
            }.collect {
                _searchState.value = it
            }
        }
    }

    fun sendIntent(intent: CommonIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }
}
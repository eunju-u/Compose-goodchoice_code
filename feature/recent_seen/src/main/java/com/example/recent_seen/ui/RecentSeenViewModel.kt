package com.example.recent_seen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.RecentUseCase
import com.example.recent_seen.ui.intent.RecentIntent
import com.example.recent_seen.ui.state.RecentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSeenViewModel @Inject constructor(
    private val recentUseCase: RecentUseCase,
) : ViewModel() {

    private val _recentUiState: MutableStateFlow<RecentUiState> =
        MutableStateFlow(RecentUiState.Loading)
    val recentUiState: StateFlow<RecentUiState> get() = _recentUiState

    private val _intent = MutableSharedFlow<RecentIntent>()
    private val intent: SharedFlow<RecentIntent> get() = _intent

//    var recentDbList = MutableStateFlow<List<StayItem>>(listOf())

    init {
        handleIntents()
        loadRecent()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.collect { myInfoIntent ->
                when (myInfoIntent) {
                    is RecentIntent.LoadRecent -> loadRecent()
                    is RecentIntent.DeleteRecent -> loadRecent()
                    is RecentIntent.Retry -> loadRecent()
                }
            }
        }
    }

    /** DB 에 저장되어있는 최근 본 상품 리스트 **/
    private fun loadRecent() = viewModelScope.launch {
        recentUseCase.getList()
            .onStart {
                _recentUiState.value = RecentUiState.Loading // 로딩
            }
            .catch { exception ->
                _recentUiState.value = RecentUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { data ->
                _recentUiState.value = RecentUiState.Success(data) // 성공
            }

    }

    /** DB 에 저장되어있는 최근 본 상품 리스트 제거 **/
    fun deleteRecentDb() = viewModelScope.launch {
        recentUseCase.deleteList()
        loadRecent()
    }

    fun sendIntent(intent: RecentIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }
}
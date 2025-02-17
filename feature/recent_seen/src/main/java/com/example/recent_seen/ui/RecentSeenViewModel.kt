package com.example.recent_seen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.StayItem
import com.example.domain.usecase.RecentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSeenViewModel @Inject constructor(
    private val recentUseCase: RecentUseCase,
) : ViewModel() {
    var recentDbList = MutableStateFlow<List<StayItem>>(listOf())

    /** DB 에 저장되어있는 최근 본 상품 리스트 **/
    fun recentDb() = viewModelScope.launch {
        recentDbList.value = recentUseCase.getList()
    }

    /** DB 에 저장되어있는 최근 본 상품 리스트 제거 **/
    fun deleteRecentDb() = viewModelScope.launch {
        recentUseCase.deleteList()
        recentDb()
    }
}
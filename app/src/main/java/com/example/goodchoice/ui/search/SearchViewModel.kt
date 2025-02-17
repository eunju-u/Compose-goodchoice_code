package com.example.goodchoice.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.info.ConnectInfo
import com.example.domain.model.RecommendAreaData
import com.example.domain.model.SearchItem
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    // 검색 > 국내 숙소 (검색 순위)
    var koreaSearchRankData: List<SearchItem> = listOf()

    // 검색 > 레저 티켓 (추천 검색어)
    var leisureSearchWordData: List<SearchItem> = listOf()

    // 검색 > 레저 티켓 (추천 지역)
    var leisureSearchAreaData: List<RecommendAreaData> = listOf()

    // 검색 데이터 요청
    fun requestSearchData() = viewModelScope.launch {
        homeUiState.value = ConnectInfo.Loading

        // 국내숙소 > 검색 순위
        koreaSearchRankData = searchUseCase.getKoreaRankData()

        // 레저*티켓 > 추천검색어 서버 조회
        leisureSearchWordData = searchUseCase.getRecommendWordData()

        leisureSearchAreaData = searchUseCase.getAreaData()

        homeUiState.value = ConnectInfo.Available()
    }
}
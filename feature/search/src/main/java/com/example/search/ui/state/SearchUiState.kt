package com.example.search.ui.state

import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem

sealed class SearchUiState {
    object Loading : SearchUiState()
    data class Success(
        val koreaRankData: List<SearchItem>, // 검색 > 국내 숙소 (검색 순위)
        val recommendWordData: List<SearchItem>, // 검색 > 레저 티켓 (추천 검색어)
        val areaData: List<RecommendAreaData> // 검색 > 레저 티켓 (추천 지역)
    ) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
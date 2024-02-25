package com.example.goodchoice.ui.search.detailSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.data.dto.*
import com.example.goodchoice.ui.search.data.KoreaSearchData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailSearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<DetailSearchConnectInfo>(DetailSearchConnectInfo.Init)
    val uiState: MutableStateFlow<DetailSearchConnectInfo>
        get() = _uiState

    private val _searchData = MutableStateFlow<List<KoreaSearchData>>(listOf())
    val searchData = _searchData.asStateFlow()

    private val resultList = listOf(
        SEOUL, SEOGWIPO, SESAN, SEOUL_STATION, HONGIK_UNI_STATION, GANGNAME_STATION,
        SEOMYEON_STATION, SINCHON_STATION, SEOHYEON_STATION, GYEONGJU, GANGNEUNG, GAPYEONG,
        GANHWA_ISLAND, KONKUK_UI_STATION, GOEJE_ISLAND, HWANGRIDAN_GIL, GONJIAM, GWANGHWAMUN,
        GURO_DIGITAL_STATION, NAMHAE, NAMYANGJU, NEST_HOTEL, NONHYEON_STATION, DAEJEON,
        DAECHEON_BEACH, DAEJEON_STATION
    )

    fun requestSearchUiData() = viewModelScope.launch {
        _uiState.value = DetailSearchConnectInfo.Loading
        withContext(coroutineContext) {
            delay(200)
        }

        // 국내숙소 > 검색 순위
        val list = listOf(
            FilterItem(filterType = "l1_1_1", filterTitle = "경주"),
            FilterItem(filterType = "l1_1_2", filterTitle = "여수"),
            FilterItem(filterType = "l1_1_3", filterTitle = "속초"),
            FilterItem(filterType = "l1_1_4", filterTitle = "전주"),
            FilterItem(filterType = "l1_1_5", filterTitle = "부산"),
            FilterItem(filterType = "l1_1_6", filterTitle = "제주도"),
            FilterItem(filterType = "l1_1_7", filterTitle = "강릉"),
            FilterItem(filterType = "l1_1_8", filterTitle = "순천"),
            FilterItem(filterType = "l1_1_9", filterTitle = "서울"),
            FilterItem(filterType = "l1_1_10", filterTitle = "춘천")
        )

        _uiState.value = DetailSearchConnectInfo.Available(list)
    }

    fun requestResultSearchData(newKey: String = "") = viewModelScope.launch {
        _searchData.value = if (newKey.isNotEmpty()) {
            resultList.filter { it.nameToKey.contains(newKey) }
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
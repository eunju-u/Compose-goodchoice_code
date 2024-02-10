package com.example.goodchoice.ui.filter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.ConnectInfo
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.domain.usecase.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase,
) : ViewModel() {
    //필터 화면 서버
    val filterUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    //선택된 숙소 유형
    var clickStayType = mutableStateOf(FilterItem())

    //숙소 유형 리스트
    var stayTypeList = LinkedList<FilterItem>()

    val selectFilterMap = HashMap<String, MutableList<FilterItem>>()
    val selectFilterList = mutableStateListOf<FilterItem>()

    //필터 후 숙소 갯수
    var stayCount = 0

//    //숙소유형 의 전체 data, 초기화 하기 위해 쓰임
//    val filterAllData = FilterItem()

    private suspend fun requestRoomTypeData() = withContext(viewModelScope.coroutineContext) {
        val data = filterUseCase.getTypeData()
        stayTypeList.clear()
        stayTypeList.addAll(data)
    }

    fun requestFilterData() = viewModelScope.launch {
        filterUiState.value = ConnectInfo.Loading
        requestRoomTypeData()

        //서버에 장소(locationCode), 숙소유형 (StayType), 필터 코드(filterCode)이 들어가야 함.
        val data = filterUseCase.getFilterData()
        filterUiState.value = ConnectInfo.Available(data)
    }
}
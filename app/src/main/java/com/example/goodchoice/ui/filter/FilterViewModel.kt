package com.example.goodchoice.ui.filter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.ConnectInfo
import com.example.goodchoice.ServerConst
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.domain.model.AroundFilterSelectedModel
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

    val selectFilterList = mutableStateListOf<FilterItem>()

    //필터 후 숙소 갯수
    var stayCount = 0
    val selectFilterMap = mutableStateMapOf<String, LinkedList<FilterItem>>()

    //주변 화면에서 선택되어 넘겨져 온 데이터
    var aroundSelectedData = AroundFilterSelectedModel()
    var checkReservation = mutableStateOf(false)

    private suspend fun requestRoomTypeData() = withContext(viewModelScope.coroutineContext) {
        val data = filterUseCase.getTypeData()
        stayTypeList.clear()
        stayTypeList.addAll(data)
    }

    /** 필터 데이터 조회
     * firstEnter : 아이템 값 눌러 조회 시 주변 화면에서 받은 intent 로 인해 재 갱신이 되지 않아 플래그 추가 **/
    fun requestFilterData(firstEnter: Boolean = false) = viewModelScope.launch {
        filterUiState.value = ConnectInfo.Loading
        requestRoomTypeData()

        //서버에 장소(locationCode), 숙소유형 (StayType), 필터 코드(filterCode)이 들어가야 함.
        val data = filterUseCase.getFilterData()
        filterUiState.value = ConnectInfo.Available(data)

        if (firstEnter) {
            //주변 화면에서 넘어온 선택된 데이터들 필터 화면에 적용
            val reservationData = aroundSelectedData.selectedReservation
            val roomData = aroundSelectedData.selectedRoom
            val priceData = aroundSelectedData.selectedPrice
            checkReservation.value = reservationData.subType?.isNotEmpty() ?: false

            //숙소 유형 체크 표시 하도록 함
            if (roomData.subType != null && roomData.subType.isNotEmpty()) {
                clickStayType.value = FilterItem(roomData.subType, roomData.text)
            } else {
                //숙소 유형 '전체' 에 체크 표시 하도록 함
                for (item in stayTypeList) {
                    if (item.filterType == ServerConst.ALL) {
                        clickStayType.value = item
                        break
                    }
                }
            }

            if (priceData.subType != null && priceData.subType.isNotEmpty()) {
                val item = FilterItem(filterType = priceData.subType, filterTitle = priceData.text)

                val value = selectFilterMap[priceData.mainType]
                    ?: LinkedList()
                value.add(item)

                priceData.mainType?.let {
                    selectFilterList.add(item)
                    selectFilterMap[it] = value
                }
            }
        }
    }
}
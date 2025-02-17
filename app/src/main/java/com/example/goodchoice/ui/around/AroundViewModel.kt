package com.example.goodchoice.ui.around

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.RoomType
import com.example.common.ServerConst
import com.example.domain.info.ConnectInfo
import com.example.domain.model.AroundFilterData
import com.example.domain.model.KoreaSearchData
import com.example.domain.model.StayItem
import com.example.domain.usecase.AroundUseCase
import com.example.goodchoice.ui.around.model.AroundFilterSelectedData
import com.example.common.ui_data.AroundFilterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AroundViewModel @Inject constructor(
    private val aroundUseCase: AroundUseCase,
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    //주변 서버 조회 시 state
    var filterList = MutableStateFlow(listOf(AroundFilterData()))
    var aroundFilterSelect = AroundFilterSelectedData()
    val selectRoomType = mutableStateOf(RoomType.SLEEP_ROOM)
    val selectSearchItem = mutableStateOf(KoreaSearchData())

    // 주변 데이터 요청
    // 숙박, 대실 클릭 했을 때만 서버 조회.
    // 처음 진입시는 서버 조회 하지 않고 하드코딩된 내용 보여줌.
    // 왜냐하면 네트워크가 없는 상태에서 주변 화면 진입 할경우 필터 리스트가 보여지고 있음.
    fun requestAroundData(isFirstEnter: Boolean = false) = viewModelScope.launch {
        //숙박, 대실 클릭 했을 때만 전체 로딩 돈다. 처음 진입 시 로딩 하지 않음.
        if (!isFirstEnter) {
            homeUiState.value = ConnectInfo.Loading

            withContext(coroutineContext) {
                delay(200)
            }
        }

        //숙박에서 사용되는 필터
        val listSleepType = aroundUseCase.getSleepData()
        val listRentalType = aroundUseCase.getRentalData()

        filterList.value =
            if (selectRoomType.value == RoomType.SLEEP_ROOM) listSleepType else listRentalType

        filterList.value.find { it.type == ServerConst.RECOMMEND }?.filterList?.let {
            //네비 메뉴 이동시 선택한 필터값 가지고 있어야 하며, 룸 타입(숙박, 대실) 클릭시 에만 초기화 필요하여 추가
            if (aroundFilterSelect.selectedRecommend.value.subType.isNullOrEmpty()) {
                val filterItem = it.first()
                val item = AroundFilterItem(
                    mainType = ServerConst.RECOMMEND,
                    subType = filterItem.type,
                    text = filterItem.text
                )
                aroundFilterSelect.selectedRecommend.value = item
            }
        }
        requestFilterStayData()
    }

    /**
     * 필터에 따른 숙소 데이터 조회
     */
    private fun requestFilterStayData() = viewModelScope.launch {
        // 서버에 request 할 파라미터는 filterList, RecommendList, roomTypeList, reservationList, priceList 로 List<String> 으로 들어간다고 가정
        // 필터는 filterList 로 해서 보내고, 나머지 추전순, 룸유형 등은 select 된 filter 리스트로 해서 보낸다.
        // TODO 서버 돌기

        // 필터에 맞는 숙소 리스트 구성 필요
        val list = listOf<StayItem>()
        homeUiState.value = ConnectInfo.Available(list)
    }
}
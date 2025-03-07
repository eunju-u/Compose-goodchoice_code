package com.example.around.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.around.domain.model.AroundFilterData
import com.example.around.domain.usecase.AroundUseCase
import com.example.around.ui.intent.AroundIntent
import com.example.around.ui.model.AroundFilterSelectedData
import com.example.common.RoomType
import com.example.common.ServerConst
import com.example.domain.model.KoreaSearchData
import com.example.domain.model.StayItem
import com.example.around.ui.state.AroundState
import com.example.around.ui.state.AroundUiState
import com.example.common.ui_data.AroundFilterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AroundViewModel @Inject constructor(
    private val aroundUseCase: AroundUseCase,
) : ViewModel() {

    private val _aroundUiState = MutableStateFlow(AroundState())
    val aroundUiState: StateFlow<AroundState> get() = _aroundUiState

    private val _intent = MutableSharedFlow<AroundIntent>()
    private val intent: SharedFlow<AroundIntent> get() = _intent

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.distinctUntilChanged() // 동일한 Intent 무시
            intent.collect { aroundIntent ->
                when (aroundIntent) {
                    is AroundIntent.LoadData -> loadData()
                    is AroundIntent.SelectRoomType -> updateRoomType(aroundIntent.roomType)
                    is AroundIntent.SelectSearchItem -> updateSearchItem(aroundIntent.searchItem)
                    is AroundIntent.ResetFilter -> resetFilter()
                    is AroundIntent.Retry -> retry()
                }
            }
        }
    }


    private fun loadData() {
        if (_aroundUiState.value.selectRoomType == RoomType.SLEEP_ROOM) {
            loadSleepData()
        } else {
            loadRentalData()
        }
    }

    // 주변 데이터 요청
    // 숙박, 대실 클릭 했을 때만 서버 조회.
    // 처음 진입시는 서버 조회 하지 않고 하드코딩된 내용 보여줌.
    // 왜냐하면 네트워크가 없는 상태에서 주변 화면 진입 할경우 필터 리스트가 보여지고 있음.
    private fun loadSleepData() = viewModelScope.launch {
        //숙박, 대실 클릭 했을 때만 전체 로딩 돈다. 처음 진입 시 로딩 하지 않음.
        _aroundUiState.update { it.copy(uiState = AroundUiState.Loading) }

        withContext(coroutineContext) {
            delay(200)
        }

        aroundUseCase.getSleepData().onStart {
            _aroundUiState.update { it.copy(uiState = AroundUiState.Loading) }
        }.catch { exception ->
            _aroundUiState.update {
                it.copy(
                    uiState = AroundUiState.Error(
                        exception.message ?: "Unknown error"
                    )
                )
            }
        }.collect { list ->
            _aroundUiState.update { it.copy(uiState = AroundUiState.Success(filterList(list))) }
        }

        requestFilterStayData()
    }

    // 대실 데이터
    private fun loadRentalData() = viewModelScope.launch {
        //숙박, 대실 클릭 했을 때만 전체 로딩 돈다. 처음 진입 시 로딩 하지 않음.
        _aroundUiState.update { it.copy(uiState = AroundUiState.Loading) }

        withContext(coroutineContext) {
            delay(200)
        }

        aroundUseCase.getRentalData().onStart {
            _aroundUiState.update { it.copy(uiState = AroundUiState.Loading) }
        }.catch { exception ->
            _aroundUiState.update {
                it.copy(
                    uiState = AroundUiState.Error(
                        exception.message ?: "Unknown error"
                    )
                )
            }
        }.collect { list ->
            _aroundUiState.update { it.copy(uiState = AroundUiState.Success(filterList(list))) }
        }
    }

    private fun filterList(list: List<AroundFilterData>): List<AroundFilterData> {
        val recommendFilterData = list.find { it.type == ServerConst.RECOMMEND }

        recommendFilterData?.filterList?.let { filterItems ->
            //네비 메뉴 이동시 선택한 필터값 가지고 있어야 하며, 룸 타입(숙박, 대실) 클릭시 에만 초기화 필요하여 추가
            val aroundFilterSelect = aroundUiState.value.aroundFilterSelect
            if (aroundFilterSelect.selectedRecommend.value.subType.isNullOrEmpty()) {
                val firstFilterItem = filterItems.firstOrNull()
                firstFilterItem?.let { filterItem ->
                    val selectedRecommend = AroundFilterItem(
                        mainType = ServerConst.RECOMMEND,
                        subType = filterItem.type,
                        text = filterItem.text
                    )
                    aroundFilterSelect.selectedRecommend.value = selectedRecommend
                }
            }
        }

        return list
    }

    private fun updateRoomType(roomType: RoomType) {
        _aroundUiState.update { it.copy(selectRoomType = roomType) }
    }

    private fun updateSearchItem(searchItem: KoreaSearchData) {
        _aroundUiState.update { it.copy(selectSearchItem = searchItem) }
    }

    private fun resetFilter() {
        _aroundUiState.update { currentState ->
            currentState.copy(aroundFilterSelect = AroundFilterSelectedData())
        }
    }

    private fun retry() {
        if (_aroundUiState.value.selectRoomType == RoomType.SLEEP_ROOM) {
            loadSleepData()
        } else {
            loadRentalData()
        }
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
    }

    fun sendIntent(intent: AroundIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }
}
package com.example.around.ui.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.common.ui_data.AroundFilterItem

//(Map으로 관리 하려 했지만, 서버에서 받아오는 내용이 아니기 때문에 select 된 아이템을 유지하기 어려워 사용 하지 않음.)
data class AroundFilterSelectedData(
    //주변 선택한 필터 > 필터는 list 로 하려다가 타입값 맞추기 위해 MutableState 로 사용
    var selectedFilter: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    //주변 선택한 필터 > 추천순
    var selectedRecommend: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    var selectedRoom: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    var selectedReservation: MutableState<AroundFilterItem> = mutableStateOf(
        AroundFilterItem()
    ),
    var selectedPrice: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
)
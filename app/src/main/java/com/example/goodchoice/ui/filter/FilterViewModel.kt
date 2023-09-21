package com.example.goodchoice.ui.filter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.FilterData
import com.example.goodchoice.api.data.FilterItem
import com.example.goodchoice.api.data.FilterListData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList

class FilterViewModel : ViewModel() {
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
        withContext(coroutineContext) {
            delay(100)
        }
        stayTypeList.clear()
        stayTypeList.addAll(
            listOf(
                FilterItem(filterCode = "r_1", filterTitle = "전체"),
                FilterItem(filterCode = "r_2", filterTitle = "홈&빌라"),
                FilterItem(filterCode = "r_3", filterTitle = "모텔"),
                FilterItem(filterCode = "r_4", filterTitle = "캠핑"),
                FilterItem(filterCode = "r_5", filterTitle = "호텔*리조트"),
                FilterItem(filterCode = "r_6", filterTitle = "게하*한옥"),
                FilterItem(filterCode = "r_7", filterTitle = "펜션")
            )
        )
    }

    fun requestFilterData() = viewModelScope.launch {
        filterUiState.value = ConnectInfo.Loading
        requestRoomTypeData()

        //서버에 장소(locationCode), 숙소유형 (StayType), 필터 코드(filterCode)이 들어가야 함.
        withContext(coroutineContext) {
            delay(200)
        }

        val data = listOf(
            FilterData(
                code = "f1",
                title = "#취향",
                list = listOf(
                    FilterListData(
                        code = "f1",
                        title = "#취향",
                        list = listOf(
                            FilterItem(filterCode = "f1_1_1", filterTitle = "#가족여행숙소"),
                            FilterItem(filterCode = "f1_1_2", filterTitle = "#스파"),
                            FilterItem(filterCode = "f1_1_3", filterTitle = "#파티룸"),
                            FilterItem(filterCode = "f1_1_4", filterTitle = "#OTT"),
                            FilterItem(filterCode = "f1_1_5", filterTitle = "#연인추천"),
                            FilterItem(filterCode = "f1_1_6", filterTitle = "#감성숙소"),
                            FilterItem(filterCode = "f1_1_7", filterTitle = "#뷰맛집"),
                            FilterItem(filterCode = "f1_1_8", filterTitle = "#연박특가"),
                            FilterItem(filterCode = "f1_1_9", filterTitle = "#리뷰좋은"),
                            FilterItem(filterCode = "f1_1_10", filterTitle = "#반려견"),
                            FilterItem(filterCode = "f1_1_11", filterTitle = "#BBQ"),
                            FilterItem(filterCode = "f1_1_12", filterTitle = "#온수풀"),
                            FilterItem(filterCode = "f1_1_13", filterTitle = "#스키장근처"),
                            FilterItem(filterCode = "f1_1_14", filterTitle = "#해돋이명소")
                        )
                    )
                )
            ),
            FilterData(
                code = "f2",
                title = "할인혜택",
                list = listOf(
                    FilterListData(
                        code = "f2",
                        title = "할인혜택",
                        list = listOf(
                            FilterItem(filterCode = "f2_1_1", filterTitle = "쿠폰할인"),
                            FilterItem(filterCode = "f2_1_2", filterTitle = "50%페이백"),
                            FilterItem(filterCode = "f2_1_3", filterTitle = "할인특가")
                        )
                    )
                )
            ),
            FilterData(
                code = "f3",
                title = "등급",
                list = listOf(
                    FilterListData(
                        code = "f3",
                        title = "등급",
                        list = listOf(
                            FilterItem(filterCode = "f3_1_1", filterTitle = "5성급"),
                            FilterItem(filterCode = "f3_1_2", filterTitle = "4성급"),
                            FilterItem(filterCode = "f3_1_3", filterTitle = "블랙"),
                            FilterItem(filterCode = "f3_1_4", filterTitle = "풀빌라")
                        )
                    )
                )
            ),
            FilterData(
                code = "f4",
                title = "가격",
                list = listOf(
                    FilterListData(
                        code = "f4",
                        title = "가격",
                        list = listOf(
                            FilterItem(filterCode = "f4_1_1", filterTitle = "~5만원"),
                            FilterItem(filterCode = "f4_1_2", filterTitle = "5~10만원"),
                            FilterItem(filterCode = "f4_1_3", filterTitle = "10~15만원"),
                            FilterItem(filterCode = "f4_1_4", filterTitle = "15~20만원"),
                            FilterItem(filterCode = "f4_1_5", filterTitle = "20~25만원"),
                            FilterItem(filterCode = "f4_1_6", filterTitle = "25~30만원"),
                            FilterItem(filterCode = "f4_1_7", filterTitle = "30만원 이상~"),
                        )
                    )
                )
            ),
            FilterData(
                code = "f6",
                title = "시설",
                list = listOf(
                    FilterListData(
                        code = "f6_1",
                        title = "공용시설",
                        list = listOf(
                            FilterItem(filterCode = "f6_1_1", filterTitle = "BBQ"),
                            FilterItem(filterCode = "f6_1_2", filterTitle = "수영장"),
                            FilterItem(filterCode = "f6_1_3", filterTitle = "주차장"),
                            FilterItem(filterCode = "f6_1_4", filterTitle = "엘레베이터"),
                            FilterItem(filterCode = "f6_1_5", filterTitle = "전자레인지"),
                            FilterItem(filterCode = "f6_1_6", filterTitle = "노래방"),
                            FilterItem(filterCode = "f6_1_7", filterTitle = "공용스파"),
                            FilterItem(filterCode = "f6_1_7", filterTitle = "카페"),
                            FilterItem(filterCode = "f6_1_8", filterTitle = "사우나"),
                            FilterItem(filterCode = "f6_1_10", filterTitle = "주방/식당"),
                            FilterItem(filterCode = "f6_1_11", filterTitle = "놀이방"),
                            FilterItem(filterCode = "f6_1_12", filterTitle = "세탁기"),
                            FilterItem(filterCode = "f6_1_13", filterTitle = "건조기"),
                            FilterItem(filterCode = "f6_1_14", filterTitle = "탈수기"),
                            FilterItem(filterCode = "f6_1_15", filterTitle = "취사가능"),
                        )
                    ),
                    FilterListData(
                        code = "f6_2",
                        title = "객실 내 시설",
                        list = listOf(
                            FilterItem(filterCode = "f6_2_1", filterTitle = "와이파이"),
                            FilterItem(filterCode = "f6_2_2", filterTitle = "객실스파"),
                            FilterItem(filterCode = "f6_2_3", filterTitle = "에어컨"),
                            FilterItem(filterCode = "f6_2_4", filterTitle = "욕실용품"),
                            FilterItem(filterCode = "f6_2_5", filterTitle = "전기밥솥"),
                            FilterItem(filterCode = "f6_2_6", filterTitle = "TV"),
                            FilterItem(filterCode = "f6_2_7", filterTitle = "미니바"),
                            FilterItem(filterCode = "f6_2_8", filterTitle = "냉장고"),
                            FilterItem(filterCode = "f6_2_9", filterTitle = "객실샤워실"),
                            FilterItem(filterCode = "f6_2_10", filterTitle = "욕조"),
                            FilterItem(filterCode = "f6_2_11", filterTitle = "드라이기"),
                            FilterItem(filterCode = "f6_2_12", filterTitle = "세탁기")
                        )
                    ),
                    FilterListData(
                        code = "f6_3",
                        title = "기타시설",
                        list = listOf(
                            FilterItem(filterCode = "f6_3_1", filterTitle = "픽업가능"),
                            FilterItem(filterCode = "f6_3_2", filterTitle = "반려견동반"),
                            FilterItem(filterCode = "f6_3_3", filterTitle = "객실내취사"),
                            FilterItem(filterCode = "f6_3_4", filterTitle = "짐보관가능"),
                            FilterItem(filterCode = "f6_3_5", filterTitle = "무료주차"),
                            FilterItem(filterCode = "f6_3_6", filterTitle = "조식포함"),
                            FilterItem(filterCode = "f6_3_7", filterTitle = "객실내흡연"),
                            FilterItem(filterCode = "f6_3_8", filterTitle = "카드결제"),
                            FilterItem(filterCode = "f6_3_9", filterTitle = "캠프파이어"),
                            FilterItem(filterCode = "f6_3_10", filterTitle = "장애인편의시설"),
                            FilterItem(filterCode = "f6_3_11", filterTitle = "금연")
                        )
                    )
                )
            )
        )

        filterUiState.value = ConnectInfo.Available(data)
    }
}
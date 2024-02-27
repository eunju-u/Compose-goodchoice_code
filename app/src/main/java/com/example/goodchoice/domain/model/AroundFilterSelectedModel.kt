package com.example.goodchoice.domain.model

import java.io.Serializable

// Filter Activity 에 넘기기 위한 데이터
data class AroundFilterSelectedModel(
    var selectedFilter: AroundFilterItem = AroundFilterItem(),
    //주변 선택한 필터 > 추천순
    var selectedRecommend: AroundFilterItem = AroundFilterItem(),
    var selectedRoom: AroundFilterItem = AroundFilterItem(),
    var selectedReservation: AroundFilterItem = AroundFilterItem(),
    var selectedPrice: AroundFilterItem = AroundFilterItem(),
) : Serializable

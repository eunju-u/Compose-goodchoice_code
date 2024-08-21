package com.example.goodchoice.data.dataSource

import com.example.domain.model.FilterItem
import com.example.goodchoice.data.dto.*
import javax.inject.Inject

class DetailSearchDataSource @Inject constructor() {
    fun getSearchData() = listOf(
        SEOUL, SEOGWIPO, SESAN, SEOUL_STATION, HONGIK_UNI_STATION, GANGNAME_STATION,
        SEOMYEON_STATION, SINCHON_STATION, SEOHYEON_STATION, GYEONGJU, GANGNEUNG, GAPYEONG,
        GANHWA_ISLAND, KONKUK_UI_STATION, GOEJE_ISLAND, HWANGRIDAN_GIL, GONJIAM, GWANGHWAMUN,
        GURO_DIGITAL_STATION, NAMHAE, NAMYANGJU, NEST_HOTEL, NONHYEON_STATION, DAEJEON,
        DAECHEON_BEACH, DAEJEON_STATION
    )

    // 국내숙소 > 검색 순위
    fun getRankData() = listOf(
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
}
package com.example.data.dataSource

import com.example.data.remote.dto.*
import com.example.data.remote.mock.DAECHEON_BEACH
import com.example.data.remote.mock.DAEJEON
import com.example.data.remote.mock.DAEJEON_STATION
import com.example.data.remote.mock.GANGNAME_STATION
import com.example.data.remote.mock.GANGNEUNG
import com.example.data.remote.mock.GANHWA_ISLAND
import com.example.data.remote.mock.GAPYEONG
import com.example.data.remote.mock.GOEJE_ISLAND
import com.example.data.remote.mock.GONJIAM
import com.example.data.remote.mock.GURO_DIGITAL_STATION
import com.example.data.remote.mock.GWANGHWAMUN
import com.example.data.remote.mock.GYEONGJU
import com.example.data.remote.mock.HONGIK_UNI_STATION
import com.example.data.remote.mock.HWANGRIDAN_GIL
import com.example.data.remote.mock.KONKUK_UI_STATION
import com.example.data.remote.mock.NAMHAE
import com.example.data.remote.mock.NAMYANGJU
import com.example.data.remote.mock.NEST_HOTEL
import com.example.data.remote.mock.NONHYEON_STATION
import com.example.data.remote.mock.SEOGWIPO
import com.example.data.remote.mock.SEOHYEON_STATION
import com.example.data.remote.mock.SEOMYEON_STATION
import com.example.data.remote.mock.SEOUL
import com.example.data.remote.mock.SEOUL_STATION
import com.example.data.remote.mock.SESAN
import com.example.data.remote.mock.SINCHON_STATION
import javax.inject.Inject

class DetailSearchDataSource @Inject constructor() {
    fun getSearchData() = listOf(
        SEOUL,
        SEOGWIPO,
        SESAN,
        SEOUL_STATION,
        HONGIK_UNI_STATION,
        GANGNAME_STATION,
        SEOMYEON_STATION,
        SINCHON_STATION,
        SEOHYEON_STATION,
        GYEONGJU,
        GANGNEUNG,
        GAPYEONG,
        GANHWA_ISLAND,
        KONKUK_UI_STATION,
        GOEJE_ISLAND,
        HWANGRIDAN_GIL,
        GONJIAM,
        GWANGHWAMUN,
        GURO_DIGITAL_STATION,
        NAMHAE,
        NAMYANGJU,
        NEST_HOTEL,
        NONHYEON_STATION,
        DAEJEON,
        DAECHEON_BEACH,
        DAEJEON_STATION
    )

    // 국내숙소 > 검색 순위
    fun getRankData() = listOf(
        FilterItemDto(filterType = "l1_1_1", filterTitle = "경주"),
        FilterItemDto(filterType = "l1_1_2", filterTitle = "여수"),
        FilterItemDto(filterType = "l1_1_3", filterTitle = "속초"),
        FilterItemDto(filterType = "l1_1_4", filterTitle = "전주"),
        FilterItemDto(filterType = "l1_1_5", filterTitle = "부산"),
        FilterItemDto(filterType = "l1_1_6", filterTitle = "제주도"),
        FilterItemDto(filterType = "l1_1_7", filterTitle = "강릉"),
        FilterItemDto(filterType = "l1_1_8", filterTitle = "순천"),
        FilterItemDto(filterType = "l1_1_9", filterTitle = "서울"),
        FilterItemDto(filterType = "l1_1_10", filterTitle = "춘천")
    )
}
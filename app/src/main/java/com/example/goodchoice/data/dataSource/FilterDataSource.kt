package com.example.goodchoice.data.dataSource

import com.example.goodchoice.ServerConst
import com.example.goodchoice.data.dto.*
import javax.inject.Inject

class FilterDataSource @Inject constructor() {
    fun getTypeData() = listOf(
        FilterItem(filterType = ServerConst.ALL, filterTitle = "전체"),
        FilterItem(filterType = ServerConst.HOUSE_AND_VILLA, filterTitle = "홈&빌라"),
        FilterItem(filterType = ServerConst.MOTEL, filterTitle = "모텔"),
        FilterItem(filterType = ServerConst.CAMPING, filterTitle = "캠핑"),
        FilterItem(filterType = ServerConst.HOTEL_AND_RESORT, filterTitle = "호텔*리조트"),
        FilterItem(filterType = ServerConst.GUESTHOUSE, filterTitle = "게하*한옥"),
        FilterItem(filterType = ServerConst.PENSION, filterTitle = "펜션")
    )

    fun getFilterData(stayType: String): List<FilterData> {
        when (stayType.ifEmpty { ServerConst.ALL }) {
            ServerConst.HOUSE_AND_VILLA -> {
                return listOf(
                    HOUSE_AND_VILLA_FAVOR,
                    HOUSE_AND_VILLA_DISCOUNT_FACILITIES,
                    ALL_PRICE,
                    ALL_FACILITIES
                )
            }
            ServerConst.MOTEL -> {
                return listOf(
                    MOTEL_FAVOR,
                    MOTEL_DISCOUNT_BENEFITS,
                    ALL_PRICE,
                    ALL_FACILITIES
                )
            }
            ServerConst.CAMPING -> {
                return listOf(
                    CAMPING_FAVOR,
                    MOTEL_DISCOUNT_BENEFITS,
                    CAMPING_TYPE,
                    ALL_PRICE,
                    ALL_FACILITIES
                )
            }
            else -> {
                return listOf(
                    ALL_FAVOR,
                    ALL_DISCOUNT_BENEFITS,
                    ALL_RANK,
                    ALL_PRICE,
                    ALL_FACILITIES
                )
            }
        }
    }
}
package com.example.filter.data.dataSource

import com.example.common.ServerConst
import com.example.filter.data.remote.mock.ALL_DISCOUNT_BENEFITS
import com.example.filter.data.remote.mock.ALL_FACILITIES
import com.example.filter.data.remote.mock.ALL_FAVOR
import com.example.filter.data.remote.mock.ALL_PRICE
import com.example.filter.data.remote.mock.ALL_RANK
import com.example.filter.data.remote.mock.CAMPING_FAVOR
import com.example.filter.data.remote.mock.CAMPING_TYPE
import com.example.filter.data.remote.mock.HOUSE_AND_VILLA_DISCOUNT_FACILITIES
import com.example.filter.data.remote.mock.HOUSE_AND_VILLA_FAVOR
import com.example.filter.data.remote.mock.MOTEL_DISCOUNT_BENEFITS
import com.example.filter.data.remote.mock.MOTEL_FAVOR
import com.example.filter.data.remote.dto.FilterDto
import com.example.filter.data.remote.dto.FilterItemDto
import javax.inject.Inject

class FilterDataSource @Inject constructor() {
    fun getTypeData() = listOf(
        FilterItemDto(
            filterType = ServerConst.ALL,
            filterTitle = "전체"
        ),
        FilterItemDto(
            filterType = ServerConst.HOUSE_AND_VILLA,
            filterTitle = "홈&빌라"
        ),
        FilterItemDto(
            filterType = ServerConst.MOTEL,
            filterTitle = "모텔"
        ),
        FilterItemDto(
            filterType = ServerConst.CAMPING,
            filterTitle = "캠핑"
        ),
        FilterItemDto(
            filterType = ServerConst.HOTEL_AND_RESORT,
            filterTitle = "호텔*리조트"
        ),
        FilterItemDto(
            filterType = ServerConst.GUESTHOUSE,
            filterTitle = "게하*한옥"
        ),
        FilterItemDto(
            filterType = ServerConst.PENSION,
            filterTitle = "펜션"
        )
    )

    fun getFilterData(stayType: String): List<FilterDto> {
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
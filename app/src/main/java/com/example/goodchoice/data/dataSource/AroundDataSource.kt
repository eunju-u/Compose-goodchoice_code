package com.example.goodchoice.data.dataSource

import com.example.goodchoice.Const
import com.example.goodchoice.data.dto.AroundFilterData
import com.example.goodchoice.data.dto.AroundFilterItem
import javax.inject.Inject

class AroundDataSource @Inject constructor() {
    fun getSleepData() = listOf(
        AroundFilterData(
            type = Const.FILTER,
            text = "필터",
            filterList = listOf(),
        ),

        AroundFilterData(
            type = Const.RECOMMEND, text = "추천순", filterList = listOf(
                AroundFilterItem(type = Const.RECOMMEND, text = "추천순"),
                AroundFilterItem(type = Const.DISTANCE, text = "거리순"),
                AroundFilterItem(type = Const.HIGH_GRADE, text = "평점높은순"),
                AroundFilterItem(type = Const.HIGH_REVIEW, text = "리뷰많은순"),
                AroundFilterItem(type = Const.ROW_PRICE, text = "낮은가격순"),
                AroundFilterItem(type = Const.HIGH_PRICE, text = "높은가격순")
            )
        ),

        AroundFilterData(
            type = Const.ROOM, text = "숙소유형", filterList = listOf(
                AroundFilterItem(type = Const.MOTEL, text = "모텔"),
                AroundFilterItem(type = Const.HOTEL_AND_RESORT, text = "호텔*리조트"),
                AroundFilterItem(type = Const.PENSION, text = "펜션"),
                AroundFilterItem(type = Const.HOUSE_AND_VILLA, text = "홈&빌라"),
                AroundFilterItem(type = Const.CAMPING, text = "캠핑"),
                AroundFilterItem(type = Const.GUESTHOUSE, text = "게하*한옥")
            )
        ),
        AroundFilterData(type = Const.RESERVATION, text = "예약가능", filterList = listOf()),

        AroundFilterData(
            type = Const.PRICE, text = "가격", filterList = listOf(
                AroundFilterItem(type = Const.LESS_5, code = "f4_1_1", text = "~5만원"),
                AroundFilterItem(type = Const.M5_L10, code = "f4_1_2", text = "5~10만원"),
                AroundFilterItem(type = Const.M10_L15, code = "f4_1_3", text = "10만원~15만원"),
                AroundFilterItem(type = Const.M15_L20, code = "f4_1_4", text = "15만원~20만원"),
                AroundFilterItem(type = Const.M20_L25, code = "f4_1_5", text = "20만원~25만원"),
                AroundFilterItem(type = Const.M25_L30, code = "f4_1_6", text = "25만원~30만원"),
                AroundFilterItem(type = Const.MORE_30, code = "f4_1_7", text = "30만원 이상~")
            )
        )
    )

    fun getRentalData() = listOf(
        AroundFilterData(
            type = Const.FILTER,
            text = "필터",
            filterList = listOf(),
        ),
        AroundFilterData(
            type = Const.RECOMMEND, text = "추천순", filterList = listOf(
                AroundFilterItem(type = Const.RECOMMEND, text = "추천순"),
                AroundFilterItem(type = Const.DISTANCE, text = "거리순"),
                AroundFilterItem(type = Const.HIGH_GRADE, text = "평점높은순"),
                AroundFilterItem(type = Const.HIGH_REVIEW, text = "리뷰많은순")
            )
        )
    )

}
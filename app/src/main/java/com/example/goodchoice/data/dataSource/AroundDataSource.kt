package com.example.goodchoice.data.dataSource

import com.example.common.ServerConst
import com.example.domain.model.AroundFilterData
import com.example.domain.model.AroundFilterItem
import javax.inject.Inject

class AroundDataSource @Inject constructor() {
    fun getSleepData() = listOf(
        AroundFilterData(
            type = ServerConst.FILTER,
            text = "필터",
            filterList = listOf(),
        ),

        AroundFilterData(
            type = ServerConst.RECOMMEND, text = "추천순", filterList = listOf(
                AroundFilterItem(
                    type = ServerConst.RECOMMEND,
                    text = "추천순"
                ),
                AroundFilterItem(
                    type = ServerConst.DISTANCE,
                    text = "거리순"
                ),
                AroundFilterItem(
                    type = ServerConst.HIGH_GRADE,
                    text = "평점높은순"
                ),
                AroundFilterItem(
                    type = ServerConst.HIGH_REVIEW,
                    text = "리뷰많은순"
                ),
                AroundFilterItem(
                    type = ServerConst.ROW_PRICE,
                    text = "낮은가격순"
                ),
                AroundFilterItem(
                    type = ServerConst.HIGH_PRICE,
                    text = "높은가격순"
                )
            )
        ),

        com.example.domain.model.AroundFilterData(
            type = ServerConst.ROOM, text = "숙소유형", filterList = listOf(
                com.example.domain.model.AroundFilterItem(type = ServerConst.MOTEL, text = "모텔"),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.HOTEL_AND_RESORT,
                    text = "호텔*리조트"
                ),
                com.example.domain.model.AroundFilterItem(type = ServerConst.PENSION, text = "펜션"),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.HOUSE_AND_VILLA,
                    text = "홈&빌라"
                ),
                com.example.domain.model.AroundFilterItem(type = ServerConst.CAMPING, text = "캠핑"),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.GUESTHOUSE,
                    text = "게하*한옥"
                )
            )
        ),
        com.example.domain.model.AroundFilterData(
            type = ServerConst.RESERVATION,
            text = "예약가능",
            filterList = listOf()
        ),

        com.example.domain.model.AroundFilterData(
            type = ServerConst.PRICE, text = "가격", filterList = listOf(
                com.example.domain.model.AroundFilterItem(type = ServerConst.LESS_5, text = "~5만원"),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.M5_L10,
                    text = "5~10만원"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.M10_L15,
                    text = "10~15만원"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.M15_L20,
                    text = "15~20만원"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.M20_L25,
                    text = "20~25만원"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.M25_L30,
                    text = "25~30만원"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.MORE_30,
                    text = "30만원 이상~"
                ),
            )
        )
    )

    fun getRentalData() = listOf(
        com.example.domain.model.AroundFilterData(
            type = ServerConst.FILTER,
            text = "필터",
            filterList = listOf(),
        ),
        com.example.domain.model.AroundFilterData(
            type = ServerConst.RECOMMEND, text = "추천순", filterList = listOf(
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.RECOMMEND,
                    text = "추천순"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.DISTANCE,
                    text = "거리순"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.HIGH_GRADE,
                    text = "평점높은순"
                ),
                com.example.domain.model.AroundFilterItem(
                    type = ServerConst.HIGH_REVIEW,
                    text = "리뷰많은순"
                )
            )
        )
    )

}
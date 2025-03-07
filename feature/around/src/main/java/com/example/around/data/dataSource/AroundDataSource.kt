package com.example.around.data.dataSource

import com.example.around.data.remote.dto.AroundFilterDto
import com.example.around.data.remote.dto.AroundFilterItemDto
import com.example.common.ServerConst
import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AroundDataSource @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getSleepData(): List<AroundFilterDto> {
        return withContext(ioDispatcher) {
            listOf(
                AroundFilterDto(
                    type = ServerConst.FILTER,
                    text = "필터",
                    filterList = listOf(),
                ),

                AroundFilterDto(
                    type = ServerConst.RECOMMEND, text = "추천순", filterList = listOf(
                        AroundFilterItemDto(
                            type = ServerConst.RECOMMEND,
                            text = "추천순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.DISTANCE,
                            text = "거리순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HIGH_GRADE,
                            text = "평점높은순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HIGH_REVIEW,
                            text = "리뷰많은순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.ROW_PRICE,
                            text = "낮은가격순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HIGH_PRICE,
                            text = "높은가격순"
                        )
                    )
                ),

                AroundFilterDto(
                    type = ServerConst.ROOM, text = "숙소유형", filterList = listOf(
                        AroundFilterItemDto(
                            type = ServerConst.MOTEL,
                            text = "모텔"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HOTEL_AND_RESORT,
                            text = "호텔*리조트"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.PENSION,
                            text = "펜션"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HOUSE_AND_VILLA,
                            text = "홈&빌라"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.CAMPING,
                            text = "캠핑"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.GUESTHOUSE,
                            text = "게하*한옥"
                        )
                    )
                ),
                AroundFilterDto(
                    type = ServerConst.RESERVATION,
                    text = "예약가능",
                    filterList = listOf()
                ),

                AroundFilterDto(
                    type = ServerConst.PRICE, text = "가격", filterList = listOf(
                        AroundFilterItemDto(
                            type = ServerConst.LESS_5,
                            text = "~5만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.M5_L10,
                            text = "5~10만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.M10_L15,
                            text = "10~15만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.M15_L20,
                            text = "15~20만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.M20_L25,
                            text = "20~25만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.M25_L30,
                            text = "25~30만원"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.MORE_30,
                            text = "30만원 이상~"
                        ),
                    )
                )
            )
        }
    }

    suspend fun getRentalData(): List<AroundFilterDto> {
        return withContext(ioDispatcher) {
            listOf(
                AroundFilterDto(
                    type = ServerConst.FILTER,
                    text = "필터",
                    filterList = listOf(),
                ),
                AroundFilterDto(
                    type = ServerConst.RECOMMEND, text = "추천순", filterList = listOf(
                        AroundFilterItemDto(
                            type = ServerConst.RECOMMEND,
                            text = "추천순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.DISTANCE,
                            text = "거리순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HIGH_GRADE,
                            text = "평점높은순"
                        ),
                        AroundFilterItemDto(
                            type = ServerConst.HIGH_REVIEW,
                            text = "리뷰많은순"
                        )
                    )
                )
            )
        }
    }
}
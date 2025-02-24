package com.example.my_info.data.dataSource

import com.example.common.Const
import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.ui_common.R
import com.example.my_info.data.remote.dto.MyInfoDto
import com.example.my_info.data.remote.dto.MyMenuDto
import com.example.my_info.data.remote.dto.MyMenuItemDto
import com.example.my_info.data.remote.dto.MyTopMenuDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyInfoDataSource @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun getMyInfoDate(): MyInfoDto {
        return withContext(ioDispatcher) {
            MyInfoDto(
                topMenuList = listOf(
                    MyTopMenuDto(
                        id = "m_1",
                        name = "최근 본 상품",
                        icon = R.drawable.ic_myinfo_recent,
                        code = Const.RECENT_HOTEL
                    ),
                    MyTopMenuDto(
                        id = "m_2",
                        name = "할인·혜택",
                        icon = R.drawable.ic_myinfo_discount
                    ),
                    MyTopMenuDto(
                        id = "m_3",
                        name = "내 리뷰",
                        icon = R.drawable.ic_myinfo_review
                    ),
                    MyTopMenuDto(
                        id = "m_4",
                        name = "알림함",
                        icon = R.drawable.ic_myinfo_noti
                    )
                ), menuList = listOf(
                    MyMenuDto(
                        id = 0,
                        title = "예약내역",
                        list = listOf(
                            MyMenuItemDto(id = 0, name = "국내 숙소"),
                            MyMenuItemDto(id = 1, name = "해외 숙소"),
                            MyMenuItemDto(id = 2, name = "공간대여"),
                            MyMenuItemDto(id = 3, name = "티켓"),
                            MyMenuItemDto(id = 4, name = "실시간렌터카"),
                            MyMenuItemDto(id = 5, name = "항공"),
                            MyMenuItemDto(id = 6, name = "항공+숙소 결합상품")
                        )
                    ),
                    MyMenuDto(
                        id = 1,
                        title = "고객센터",
                        list = listOf(
                            MyMenuItemDto(
                                id = 0,
                                icon = R.drawable.ic_myinfo_question,
                                name = "자주 묻는 질문"
                            ),
                            MyMenuItemDto(
                                id = 1,
                                icon = R.drawable.ic_myinfo_cs_message,
                                name = "1:1 카카오 문의",
                                subText = "오전9시 ~ 새벽3시"
                            ),
                            MyMenuItemDto(
                                id = 2,
                                icon = R.drawable.ic_myinfo_cs_call,
                                name = "고객행복센터 연결",
                                subText = "오전9시 ~ 새벽3시"
                            ),
                        )
                    ),
                    MyMenuDto(
                        id = 2,
                        path = "banner_path",
                        list = listOf(
                            MyMenuItemDto(id = 0, name = "공지사항"),
                            MyMenuItemDto(id = 1, name = "여기어때 상품권 잔액조회"),
                            MyMenuItemDto(id = 2, name = "리서치 참여"),
                            MyMenuItemDto(id = 3, name = "기업 출장/복지 서비스 문의"),
                            MyMenuItemDto(id = 4, name = "트립홀릭 혜택 안내"),
                            MyMenuItemDto(id = 5, name = "엘리트 혜택 안내"),
                            MyMenuItemDto(id = 6, name = "비즈니스 혜택 안내"),
                            MyMenuItemDto(id = 7, name = "혜택 QR")
                        )
                    ), MyMenuDto(
                        id = 3,
                        list = listOf(
                            MyMenuItemDto(id = 0, name = "설정"),
                            MyMenuItemDto(id = 1, name = Const.LOGOUT)
                        )
                    )
                )
            )
        }
    }
}
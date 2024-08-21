package com.example.goodchoice.data.dataSource

import com.example.common.Const
import com.example.common.R
import com.example.domain.model.CategoryItem
import com.example.domain.model.MyInfoData
import com.example.domain.model.MyMenuData
import com.example.domain.model.MyMenuItem
import javax.inject.Inject

class MyInfoDataSource @Inject constructor() {
    fun getMyInfoDate() = MyInfoData(
        topMenuList = listOf(
            CategoryItem(
                id = "m_1",
                name = "최근 본 상품",
                icon = R.drawable.ic_myinfo_recent,
                code = Const.RECENT_HOTEL
            ),
            CategoryItem(
                id = "m_2",
                name = "할인·혜택",
                icon = R.drawable.ic_myinfo_discount
            ),
            CategoryItem(
                id = "m_3",
                name = "내 리뷰",
                icon = R.drawable.ic_myinfo_review
            ),
            CategoryItem(
                id = "m_4",
                name = "알림함",
                icon = R.drawable.ic_myinfo_noti
            )
        ), menuList = listOf(
            MyMenuData(
                id = 0,
                title = "예약내역",
                list = listOf(
                    MyMenuItem(id = 0, name = "국내 숙소"),
                    MyMenuItem(id = 1, name = "해외 숙소"),
                    MyMenuItem(id = 2, name = "공간대여"),
                    MyMenuItem(id = 3, name = "티켓"),
                    com.example.domain.model.MyMenuItem(id = 4, name = "실시간렌터카"),
                    com.example.domain.model.MyMenuItem(id = 5, name = "항공"),
                    com.example.domain.model.MyMenuItem(id = 6, name = "항공+숙소 결합상품")
                )
            ),
            com.example.domain.model.MyMenuData(
                id = 1,
                title = "고객센터",
                list = listOf(
                    com.example.domain.model.MyMenuItem(
                        id = 0,
                        icon = R.drawable.ic_myinfo_question,
                        name = "자주 묻는 질문"
                    ),
                    com.example.domain.model.MyMenuItem(
                        id = 1,
                        icon = R.drawable.ic_myinfo_cs_message,
                        name = "1:1 카카오 문의",
                        subText = "오전9시 ~ 새벽3시"
                    ),
                    com.example.domain.model.MyMenuItem(
                        id = 2,
                        icon = R.drawable.ic_myinfo_cs_call,
                        name = "고객행복센터 연결",
                        subText = "오전9시 ~ 새벽3시"
                    ),
                )
            ),
            /**
             * banner (?)
             * */
            /**
             * banner (?)
             * */
            /**
             * banner (?)
             * */
            /**
             * banner (?)
             * */
            com.example.domain.model.MyMenuData(
                id = 2,
                path = "banner_path",
                list = listOf(
                    com.example.domain.model.MyMenuItem(id = 0, name = "공지사항"),
                    com.example.domain.model.MyMenuItem(id = 1, name = "여기어때 상품권 잔액조회"),
                    com.example.domain.model.MyMenuItem(id = 2, name = "리서치 참여"),
                    com.example.domain.model.MyMenuItem(id = 3, name = "기업 출장/복지 서비스 문의"),
                    com.example.domain.model.MyMenuItem(id = 4, name = "트립홀릭 혜택 안내"),
                    com.example.domain.model.MyMenuItem(id = 5, name = "엘리트 혜택 안내"),
                    com.example.domain.model.MyMenuItem(id = 6, name = "비즈니스 혜택 안내"),
                    com.example.domain.model.MyMenuItem(id = 7, name = "혜택 QR")
                )
            ), com.example.domain.model.MyMenuData(
                id = 3,
                list = listOf(
                    com.example.domain.model.MyMenuItem(id = 0, name = "설정"),
                    com.example.domain.model.MyMenuItem(id = 1, name = Const.LOGOUT)
                )
            )
        )
    )
}
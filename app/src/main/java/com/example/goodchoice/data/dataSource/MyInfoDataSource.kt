package com.example.goodchoice.data.dataSource

import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.data.dto.CategoryItem
import com.example.goodchoice.data.dto.MyInfoData
import com.example.goodchoice.data.dto.MyMenuData
import com.example.goodchoice.data.dto.MyMenuItem
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
            CategoryItem(id = "m_2", name = "할인·혜택", icon = R.drawable.ic_myinfo_discount),
            CategoryItem(id = "m_3", name = "내 리뷰", icon = R.drawable.ic_myinfo_review),
            CategoryItem(id = "m_4", name = "알림함", icon = R.drawable.ic_myinfo_noti)
        ), menuList = listOf(
            MyMenuData(
                id = 0,
                title = "예약내역",
                list = listOf(
                    MyMenuItem(id = 0, name = "국내 숙소"),
                    MyMenuItem(id = 1, name = "해외 숙소"),
                    MyMenuItem(id = 2, name = "공간대여"),
                    MyMenuItem(id = 3, name = "티켓"),
                    MyMenuItem(id = 4, name = "실시간렌터카"),
                    MyMenuItem(id = 5, name = "항공"),
                    MyMenuItem(id = 6, name = "항공+숙소 결합상품")
                )
            ),
            MyMenuData(
                id = 1,
                title = "고객센터",
                list = listOf(
                    MyMenuItem(id = 0, icon = R.drawable.ic_myinfo_question, name = "자주 묻는 질문"),
                    MyMenuItem(
                        id = 1,
                        icon = R.drawable.ic_myinfo_cs_message,
                        name = "1:1 카카오 문의",
                        subText = "오전9시 ~ 새벽3시"
                    ),
                    MyMenuItem(
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
            MyMenuData(
                id = 2,
                path = "banner_path",
                list = listOf(
                    MyMenuItem(id = 0, name = "공지사항"),
                    MyMenuItem(id = 1, name = "여기어때 상품권 잔액조회"),
                    MyMenuItem(id = 2, name = "리서치 참여"),
                    MyMenuItem(id = 3, name = "기업 출장/복지 서비스 문의"),
                    MyMenuItem(id = 4, name = "트립홀릭 혜택 안내"),
                    MyMenuItem(id = 5, name = "엘리트 혜택 안내"),
                    MyMenuItem(id = 6, name = "비즈니스 혜택 안내"),
                    MyMenuItem(id = 7, name = "혜택 QR")
                )
            ), MyMenuData(
                id = 3,
                list = listOf(
                    MyMenuItem(id = 0, name = "설정"),
                    MyMenuItem(id = 1, name = Const.LOGOUT)
                )
            )
        )
    )
}
package com.example.common.data

import com.example.common.Const

sealed class TabData(
    val name: String = "",
    val route: String =""
) {
    //찜 탭
    object KOREA : TabData(name = "국내여행", route = Const.KOREA)
    object OVERSEA : TabData(name = "해외여행", route = Const.OVERSEA)
    object RENTAL : TabData(name = "공간대여", route = Const.SPACE_RENTAL)
    object LEISURE : TabData(name =  "레저*티켓", route = Const.LEISURE_AND_TICKET)

    //검색 탭
    object KOREA_STAY : TabData(name = "국내숙소", route = Const.KOREA_STAY)
    object OVERSEA_STAY : TabData(name = "해외숙소", route = Const.OVERSEA_STAY)
}
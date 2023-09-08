package com.example.goodchoice.ui

import com.example.goodchoice.Const

sealed class TabData(
    val name: String = "",
    val route: String =""
) {
    object KOREA : TabData(name = "국내여행", route = Const.KOREA)
    object OVERSEA : TabData(name = "해외여행", route = Const.OVERSEA)
    object RENTAL : TabData(name = Const.C_SPACE_RENTAL, route = Const.SPACE_RENTAL)
    object LEISURE : TabData(name =  Const.C_LEISURE_AND_TICKET, route = Const.LEISURE_AND_TICKET)
}
package com.example.my_info.data.mapper

import com.example.my_info.domain.model.MyInfoData
import com.example.my_info.domain.model.MyMenuData
import com.example.my_info.domain.model.MyMenuItem
import com.example.my_info.data.remote.dto.MyInfoDto
import com.example.my_info.data.remote.dto.MyMenuDto
import com.example.my_info.data.remote.dto.MyMenuItemDto
import com.example.my_info.data.remote.dto.MyTopMenuDto
import com.example.my_info.domain.model.MyTopMenuItem

fun MyInfoDto.generateMyInfoData(): MyInfoData {
    val item = this@generateMyInfoData

    return MyInfoData(
        topMenuList = item.topMenuList?.map { it.generateMyTopMenuItem() },
        menuList = item.menuList?.map { it.generateMyMenuData() }
    )
}

fun MyTopMenuDto.generateMyTopMenuItem(): MyTopMenuItem {
    val item = this@generateMyTopMenuItem

    return MyTopMenuItem(
        id = item.id,
        name = item.name,
        icon = item.icon,
        code = item.code,
    )
}

fun MyMenuDto.generateMyMenuData(): MyMenuData {
    val item = this@generateMyMenuData

    return MyMenuData(
        id = item.id,
        title = item.title,
        path = item.path,
        list = item.list?.map {
            it.generateMyMenuItem()
        }
    )
}

fun MyMenuItemDto.generateMyMenuItem(): MyMenuItem {
    val item = this@generateMyMenuItem

    return MyMenuItem(
        id = item.id,
        icon = item.icon,
        name = item.name,
        subText = item.subText,
    )
}

package com.example.data.mapper

import com.example.data.remote.dto.MyInfoDto
import com.example.data.remote.dto.MyMenuDto
import com.example.data.remote.dto.MyMenuItemDto
import com.example.domain.model.MyInfoData
import com.example.domain.model.MyMenuData
import com.example.domain.model.MyMenuItem

fun MyInfoDto.generateData(): MyInfoData {
    val item = this@generateData

    return MyInfoData(
        topMenuList = item.topMenuList?.map { it.generateData() },
        menuList = item.menuList?.map { it.generateData() }
    )
}

fun MyMenuDto.generateData(): MyMenuData {
    val item = this@generateData

    return MyMenuData(
        id = item.id,
        title = item.title,
        path = item.path,
        list = item.list?.map {
            it.generateData()
        }
    )
}

fun MyMenuItemDto.generateData(): MyMenuItem {
    val item = this@generateData

    return MyMenuItem(
        id = item.id,
        icon = item.icon,
        name = item.name,
        subText = item.subText,
    )
}

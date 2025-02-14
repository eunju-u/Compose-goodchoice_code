package com.example.data.mapper

import com.example.data.remote.dto.MyInfoDto
import com.example.data.remote.dto.MyMenuDto
import com.example.data.remote.dto.MyMenuItemDto
import com.example.domain.model.MyInfoData
import com.example.domain.model.MyMenuData
import com.example.domain.model.MyMenuItem

fun MyInfoDto.generateMyInfoData(): MyInfoData {
    val item = this@generateMyInfoData

    return MyInfoData(
        topMenuList = item.topMenuList?.map { it.generateCategoryItem() },
        menuList = item.menuList?.map { it.generateMyMenuData() }
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

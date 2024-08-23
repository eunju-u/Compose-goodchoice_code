package com.example.data.mapper

import com.example.data.remote.dto.FilterDto
import com.example.data.remote.dto.FilterItemDto
import com.example.domain.model.FilterData
import com.example.domain.model.FilterItem
import com.example.domain.model.FilterListData

fun FilterDto.generateData(): FilterData {
    val item = this@generateData

    return FilterData(
        code = item.code,
        title = item.title,
        list = item.list?.map {
            FilterListData(
                code = it.code,
                title = it.title,
                list = it.list?.map { item ->
                    item.generateData()
                }
            )
        },
    )
}


fun FilterItemDto.generateData(): FilterItem {
    val item = this@generateData

    return FilterItem(
        filterType = item.filterType,
        filterTitle = item.filterTitle
    )
}
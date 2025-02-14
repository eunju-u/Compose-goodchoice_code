package com.example.data.mapper

import com.example.data.remote.dto.FilterDto
import com.example.data.remote.dto.FilterItemDto
import com.example.domain.model.FilterData
import com.example.domain.model.FilterItem
import com.example.domain.model.FilterListData

fun FilterDto.generateFilterData(): FilterData {
    val item = this@generateFilterData

    return FilterData(
        code = item.code,
        title = item.title,
        list = item.list?.map {
            FilterListData(
                code = it.code,
                title = it.title,
                list = it.list?.map { item ->
                    item.generateFilterItem()
                }
            )
        },
    )
}


fun FilterItemDto.generateFilterItem(): FilterItem {
    val item = this@generateFilterItem

    return FilterItem(
        filterType = item.filterType,
        filterTitle = item.filterTitle
    )
}
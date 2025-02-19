package com.example.around.data.mapper

import com.example.around.data.remote.dto.AroundFilterDto
import com.example.around.domain.model.AroundFilterData
import com.example.around.domain.model.AroundFilterItem

fun AroundFilterDto.generateAroundFilterData(): AroundFilterData {
    val item = this@generateAroundFilterData

    return AroundFilterData(
        type = item.type,
        text = item.text,
        filterList = item.filterList?.map {
            AroundFilterItem(
                type = it.type,
                text = it.text
            )
        })
}


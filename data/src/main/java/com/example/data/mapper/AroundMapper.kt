package com.example.data.mapper

import com.example.data.remote.dto.AroundFilterDto
import com.example.domain.model.AroundFilterData
import com.example.domain.model.AroundFilterItem

fun AroundFilterDto.generateData(): AroundFilterData {
    val item = this@generateData

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


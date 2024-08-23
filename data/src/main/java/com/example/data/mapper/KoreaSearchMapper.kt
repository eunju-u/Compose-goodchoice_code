package com.example.data.mapper

import com.example.data.remote.dto.KoreaSearchDto
import com.example.domain.model.KoreaSearchData

fun KoreaSearchDto.generateData(): KoreaSearchData {
    val item = this@generateData

    return KoreaSearchData(
        id = item.id,
        name = item.name,
        city = item.city,
        type = item.type,
        location = item.location
    )
}

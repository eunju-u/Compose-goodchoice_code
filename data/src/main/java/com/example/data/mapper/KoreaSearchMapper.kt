package com.example.data.mapper

import com.example.data.remote.dto.KoreaSearchDto
import com.example.domain.model.KoreaSearchData

fun KoreaSearchDto.generateKoreaSearchData(): KoreaSearchData {
    val item = this@generateKoreaSearchData

    return KoreaSearchData(
        id = item.id,
        name = item.name,
        city = item.city,
        type = item.type,
        location = item.location
    )
}

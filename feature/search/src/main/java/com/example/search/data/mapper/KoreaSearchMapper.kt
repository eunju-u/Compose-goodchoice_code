package com.example.search.data.mapper

import com.example.domain.model.KoreaSearchData
import com.example.search.data.remote.dto.KoreaSearchDto

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

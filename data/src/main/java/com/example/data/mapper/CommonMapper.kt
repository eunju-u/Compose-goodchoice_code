package com.example.data.mapper

import com.example.data.remote.dto.StayItemDto
import com.example.domain.model.StayItem

fun StayItemDto.generateStayItem(): StayItem {
    val item = this@generateStayItem

    return StayItem(
        id = item.id,
        label = item.label,
        name = item.name,
        star = item.star,
        commentCount = item.commentCount,
        location = item.location,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        mainImage = item.mainImage,
        level = item.level,
    )
}
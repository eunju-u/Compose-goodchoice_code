package com.example.data.mapper

import com.example.database.recent.RecentDbItem
import com.example.domain.model.StayItem

fun RecentDbItem.generateStayItem(): StayItem {
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
        level = item.level
    )
}

fun StayItem.generateRecentDbItem(): RecentDbItem {
    val item = this@generateRecentDbItem

    return RecentDbItem(
        id = item.id ?: "",
        label = item.label ?: "",
        name = item.name ?: "",
        star = item.star ?: "",
        commentCount = item.commentCount ?: 0,
        location = item.location ?: "",
        discountPer = item.discountPer ?: 0,
        defaultPrice = item.defaultPrice ?: "",
        discountPrice = item.discountPrice ?: "",
        mainImage = item.mainImage ?: "",
        level = item.level ?: ""
    )
}
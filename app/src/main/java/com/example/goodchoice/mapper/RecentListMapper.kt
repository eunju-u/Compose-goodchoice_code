package com.example.goodchoice.mapper

import com.example.domain.model.StayItem
import com.example.database.recent.RecentDbItem

fun com.example.database.recent.RecentDbItem.generateData(): com.example.domain.model.StayItem {
    val item = this@generateData

    return com.example.domain.model.StayItem(
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

fun com.example.domain.model.StayItem.generateData(): com.example.database.recent.RecentDbItem {
    val item = this@generateData

    return com.example.database.recent.RecentDbItem(
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
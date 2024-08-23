package com.example.data.mapper

import com.example.database.like.LikeDbItem
import com.example.domain.model.LikeItem

fun LikeDbItem.generateData(): LikeItem {
    val item = this@generateData

    return LikeItem(
        id = item.id
    )
}
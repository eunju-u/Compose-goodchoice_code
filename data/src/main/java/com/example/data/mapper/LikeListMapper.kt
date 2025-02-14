package com.example.data.mapper

import com.example.database.like.LikeDbItem
import com.example.domain.model.LikeItem

fun LikeDbItem.generateLikeItem(): LikeItem {
    val item = this@generateLikeItem

    return LikeItem(
        id = item.id
    )
}
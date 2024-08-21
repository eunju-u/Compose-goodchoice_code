package com.example.goodchoice.mapper

import com.example.domain.model.LikeItem
import com.example.goodchoice.db.like.LikeDbItem

fun LikeDbItem.generateData(): com.example.domain.model.LikeItem {
    val item = this@generateData

    return com.example.domain.model.LikeItem(
        id = item.id
    )
}
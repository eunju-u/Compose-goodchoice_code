package com.example.goodchoice.mapper

import com.example.goodchoice.data.dto.LikeItem
import com.example.goodchoice.db.like.LikeDbItem

fun LikeDbItem.generateData(): LikeItem {
    val item = this@generateData

    return LikeItem(
        id = item.id
    )
}
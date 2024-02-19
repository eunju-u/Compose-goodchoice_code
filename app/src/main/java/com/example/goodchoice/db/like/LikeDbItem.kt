package com.example.goodchoice.db.like

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikeDbItem(
    @PrimaryKey
    val id: String = "",
)
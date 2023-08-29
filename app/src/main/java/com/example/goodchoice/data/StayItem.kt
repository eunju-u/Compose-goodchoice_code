package com.example.goodchoice.data

data class StayItem(
    val label: String? = "",
    val imageUrl: String? = "",
    val name: String? = "",
    val star: String? = "",
    val commentCount: Int? = 0,
    val location: String? = "",
    val discountPer:Int? = 0,
    val defaultPrice: Int? = 0,
    val discountPrice: Int? = 0
)
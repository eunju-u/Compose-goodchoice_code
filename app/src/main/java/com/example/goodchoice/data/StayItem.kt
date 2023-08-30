package com.example.goodchoice.data

data class StayItem(
    val label: String? = "",
    val imageUrl: String? = "",
    val name: String? = "",
    val star: String? = "",
    val commentCount: Int? = 0,
    val location: String? = "",
    val discountPer: Int? = 0,
    val defaultPrice: String? = "", //String 으로 변환한 이유 : 가격 값에 "다른 날짜 확인" 이 들어올 수 있기 때문
    val discountPrice: String? = ""
)
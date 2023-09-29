package com.example.goodchoice.api.data

data class RoomItem(
    val id: String? = "",
    val name: String? = "",
    val info: String? = "",
    val addInfo: String? = "",
    val discountPer: Int? = 0,
    val defaultPrice: String? = "", //String 으로 변환한 이유 : 가격 값에 "다른 날짜 확인" 이 들어올 수 있기 때문
    val discountPrice: String? = "",
    val inTime: String? = "", //입실시간
    val outTime: String? = "", //퇴실시간
    val coupon: String? = "",
    val count: Int? = 0, //룸 갯수
    val image: String? = "" //이미지
)
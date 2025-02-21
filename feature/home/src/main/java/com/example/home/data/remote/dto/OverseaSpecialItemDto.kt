package com.example.home.data.remote.dto

data class OverseaSpecialItemDto(
    val id: Int? = 0,
    val name: String? = "", //숙소 이름
    val discountPer: Int? = 0, //할인율
    val defaultPrice: String? = "", //기본 가격 String 으로 변환한 이유 : 가격 값에 "다른 날짜 확인" 이 들어올 수 있기 때문
    val discountPrice: String? = "", //할인 가격
    val mainImage: String? = "", //메인 이미지
)
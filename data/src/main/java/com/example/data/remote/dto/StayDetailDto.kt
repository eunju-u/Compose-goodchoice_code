package com.example.data.remote.dto

data class StayDetailDto(
    val id: String? = "",
    val label: String? = "", //해외숙소, 국내숙소
    val name: String? = "", //숙소 이름
    val star: String? = "", //별점
    val commentCount: Int? = 0, //리뷰 갯수
    val location: String? = "", //위치
    val discountPer: Int? = 0, //할인율
    val defaultPrice: String? = "", //기본 가격 String 으로 변환한 이유 : 가격 값에 "다른 날짜 확인" 이 들어올 수 있기 때문
    val discountPrice: String? = "", //할인 가격
    val imageList: List<String>? = emptyList(), //이미지
    val level: String? = "", //성급
    val coupon: String? = "", //쿠폰
    val payList: List<PayDto>? = emptyList(),
    val roomList: List<RoomItemDto>? = emptyList(), //방
    val message: String? = "", //주인 메시지
    val defaultMessage: String? = "", //기본정보 웹뷰에 노출될 string
    val aroundInfo: String? = "", //주변정보
    val service: List<ServiceDto>? = emptyList() //편의시설 및 서비스
)
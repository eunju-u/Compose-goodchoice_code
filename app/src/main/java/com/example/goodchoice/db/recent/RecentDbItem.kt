package com.example.goodchoice.db.recent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentDbItem(
    @PrimaryKey
    val id: String = "",
    val label: String = "", //해외숙소, 국내숙소
    val name: String = "", //숙소 이름
    val star: String = "", //별점
    val commentCount: Int = 0, //리뷰 갯수
    val location: String = "", //위치
    val discountPer: Int = 0, //할인율
    val defaultPrice: String = "", //기본 가격 String 으로 변환한 이유 : 가격 값에 "다른 날짜 확인" 이 들어올 수 있기 때문
    val discountPrice: String = "", //할인 가격
    val mainImage: String = "", //메인 이미지
    val level: String = "", //성급
)
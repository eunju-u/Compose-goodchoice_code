package com.example.goodchoice.data.dto

data class UserInfoData(
    val userId: String? = "",
    val userName: String? = "",
    val likeList: List<Int>? = emptyList() //유저 찜 목록
)
package com.example.data.remote.dto

data class UserInfoDto(
    val userId: String? = "",
    val userName: String? = "",
    val likeList: List<Int>? = emptyList() //유저 찜 목록
)
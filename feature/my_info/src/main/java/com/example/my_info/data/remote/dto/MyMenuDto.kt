package com.example.my_info.data.remote.dto

data class MyMenuDto (
    val id : Int = 0,
    val title : String = "",
    val path : String = "",
    val list : List<MyMenuItemDto>? = emptyList()
)
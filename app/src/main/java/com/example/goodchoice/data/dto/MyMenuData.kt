package com.example.goodchoice.data.dto

data class MyMenuData (
    val id : Int = 0,
    val title : String = "",
    val path : String = "",
    val list : List<MyMenuItem>? = emptyList()
)
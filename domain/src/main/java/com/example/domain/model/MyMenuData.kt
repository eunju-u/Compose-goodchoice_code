package com.example.domain.model

data class MyMenuData (
    val id : Int = 0,
    val title : String = "",
    val path : String = "",
    val list : List<MyMenuItem>? = emptyList()
)
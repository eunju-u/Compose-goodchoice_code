package com.example.goodchoice.ui.search.data

import java.io.Serializable

data class KoreaSearchData(
    val id: Int? = 0,
    val name: String? = "",
    val city: String? = "",
    val type: String? = "",
    val location: String? = ""
) : Serializable {
    val nameToKey = "$name$city"
}
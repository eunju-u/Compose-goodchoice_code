package com.example.goodchoice.ui.search.data

data class KoreaSearchData(
    val id: Int? = 0,
    val name: String? = "",
    val city: String? = "",
    val type: String? = "",
    val location: String? = ""
) {
    val nameToKey = "$name$city"
}
package com.example.search.data.remote.dto

import java.io.Serializable

data class KoreaSearchDto(
    val id: Int? = 0,
    val name: String? = "",
    val city: String? = "",
    val type: String? = "",
    val location: String? = ""
) : Serializable {
    val nameToKey = "$name$city"
}
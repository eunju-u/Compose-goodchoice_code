package com.example.goodchoice.domain.model

import java.io.Serializable

data class AroundFilterItem(
    val mainType: String? = "",
    val subType: String? = "",
    val text: String? = ""
): Serializable
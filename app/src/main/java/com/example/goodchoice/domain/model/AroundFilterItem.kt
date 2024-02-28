package com.example.goodchoice.domain.model

import java.io.Serializable

data class AroundFilterItem(
    var mainType: String? = "",
    var subType: String? = "",
    var text: String? = ""
): Serializable
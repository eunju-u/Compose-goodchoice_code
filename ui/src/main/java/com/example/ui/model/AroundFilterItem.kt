package com.example.ui.model

import java.io.Serializable

data class AroundFilterItem(
    var mainType: String? = "",
    var subType: String? = "",
    var text: String? = ""
): Serializable
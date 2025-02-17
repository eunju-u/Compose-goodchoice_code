package com.example.common.ui_data

import java.io.Serializable

data class AroundFilterItem(
    var mainType: String? = "",
    var subType: String? = "",
    var text: String? = ""
): Serializable
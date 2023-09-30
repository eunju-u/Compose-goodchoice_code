package com.example.goodchoice.api.data

import java.io.Serializable

data class ServiceData(
    val type: String? = "",
    val name: String? = ""
) : Serializable
package com.example.stay_detail.domain.model

import java.io.Serializable

data class ServiceData(
    val type: String? = "",
    val name: String? = ""
) : Serializable
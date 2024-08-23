package com.example.data.remote.dto

import java.io.Serializable

data class ServiceDto(
    val type: String? = "",
    val name: String? = ""
) : Serializable
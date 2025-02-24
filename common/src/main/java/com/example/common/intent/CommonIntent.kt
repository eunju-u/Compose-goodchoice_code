package com.example.common.intent

sealed class CommonIntent {
    object LoadMyInfo : CommonIntent()
    data class Retry(val reason: String) : CommonIntent()
}
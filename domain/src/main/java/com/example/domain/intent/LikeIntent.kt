package com.example.domain.intent

sealed class LikeIntent {
    object LoadLikeData : LikeIntent()
    data class ToggleLike(val stayId: String) : LikeIntent()
    data class Retry(val reason: String) : LikeIntent()
}

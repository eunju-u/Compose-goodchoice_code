package com.example.recent_seen.ui.intent

sealed class RecentIntent {
    object LoadRecent : RecentIntent()
    object DeleteRecent : RecentIntent()
    data class Retry(val reason: String) : RecentIntent()
}
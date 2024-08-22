package com.example.goodchoice.data.dataSource

import android.content.Context
import com.example.database.recent.RecentDb
import com.example.database.recent.RecentDbItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecentSeenDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun getList(): List<com.example.database.recent.RecentDbItem> {
        val recentDb = com.example.database.recent.RecentDb.getInstance(context)
        return recentDb?.userDao()?.getAll()?: listOf()
    }
    fun deleteList() {
        val recentDb = com.example.database.recent.RecentDb.getInstance(context)
        recentDb?.userDao()?.deleteAll()
    }
}
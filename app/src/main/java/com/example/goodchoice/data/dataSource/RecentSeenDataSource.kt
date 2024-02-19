package com.example.goodchoice.data.dataSource

import android.content.Context
import com.example.goodchoice.db.recent.RecentDb
import com.example.goodchoice.db.recent.RecentDbItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecentSeenDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun getList(): List<RecentDbItem> {
        val recentDb = RecentDb.getInstance(context)
        return recentDb?.userDao()?.getAll()?: listOf()
    }
    fun deleteList() {
        val recentDb = RecentDb.getInstance(context)
        recentDb?.userDao()?.deleteAll()
    }
}
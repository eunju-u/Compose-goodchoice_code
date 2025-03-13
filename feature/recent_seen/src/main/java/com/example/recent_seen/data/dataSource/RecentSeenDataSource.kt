package com.example.recent_seen.data.dataSource

import android.content.Context
import com.example.common.network.Dispatcher
import com.example.common.network.Dispatchers
import com.example.database.recent.RecentDb
import com.example.database.recent.RecentDbItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentSeenDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    ) {
    suspend fun getList(): List<RecentDbItem> {
        return withContext(ioDispatcher) {
            val recentDb = RecentDb.getInstance(context)
            recentDb?.userDao()?.getAll()?: listOf()
        }

    }
    fun deleteList() {
        val recentDb = RecentDb.getInstance(context)
        recentDb?.userDao()?.deleteAll()
    }
}
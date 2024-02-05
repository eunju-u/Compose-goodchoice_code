package com.example.goodchoice.ui.recentSeen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.goodchoice.api.data.StayItem
import com.example.goodchoice.db.RecentDb
import com.example.goodchoice.mapper.generateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecentSeenViewModel : ViewModel() {
    var recentDbList = MutableStateFlow<List<StayItem>>(listOf())

    /** DB 에 저장되어있는 최근 본 상품 리스트 **/
    fun recentDb(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            val recentDb = RecentDb.getInstance(context)
            val list = recentDb?.userDao()?.getAll()
            val recentList = list?.map {
                it.generateData()
            }
            recentDbList.value = recentList ?: listOf()
        }

    /** DB 에 저장되어있는 최근 본 상품 리스트 제거 **/
    fun deleteRecentDb(context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val recentDb = RecentDb.getInstance(context)
        recentDb?.userDao()?.deleteAll()
        recentDb(context)
    }
}
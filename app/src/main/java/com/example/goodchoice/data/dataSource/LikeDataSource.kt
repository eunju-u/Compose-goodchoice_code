package com.example.goodchoice.data.dataSource

import com.example.goodchoice.GoodChoiceApplication
import com.example.goodchoice.data.dto.*
import com.example.goodchoice.db.like.LikeDb
import com.example.goodchoice.mapper.generateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class LikeDataSource @Inject constructor() {
    suspend fun getLikeData(): List<StayItem> {

        val list = CoroutineScope(Dispatchers.IO).async {
            val likeDb = LikeDb.getInstance(GoodChoiceApplication.instance)
            likeDb?.userDao()?.getAll()?.map {
                it.generateData()
            }
        }
        val likeList = list.await() ?: listOf()
        return getStayAllData().filter { stayItem ->
            likeList.any { likeItem ->
                stayItem.id == likeItem.id
            }
        }
    }

    private fun getStayAllData() = listOf(
        S_1, S_2, S_3, S_4, S_5, S_6, S_7, S_8, S_9, S_10, S_11
    )
}
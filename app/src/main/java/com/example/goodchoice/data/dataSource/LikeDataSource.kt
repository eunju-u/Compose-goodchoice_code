package com.example.goodchoice.data.dataSource

import com.example.goodchoice.GoodChoiceApplication
import com.example.goodchoice.data.dto.*
import com.example.goodchoice.db.like.LikeDb
import com.example.goodchoice.db.like.LikeDbItem
import com.example.goodchoice.mapper.generateData
import kotlinx.coroutines.*
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

    /** 찜 DB 에서 해당 내용이 있는지 여부 **/
    fun hasLikeData(stayItemId: String): Boolean {
        val likeDb = LikeDb.getInstance(GoodChoiceApplication.instance)
        val count = likeDb?.userDao()?.hasLike(stayItemId) ?: 0
        return count > 0
    }

    /** 찜 DB 에 내용 저장 **/
    fun insertLikeData(item: LikeDbItem) {
        val likeDb = LikeDb.getInstance(GoodChoiceApplication.instance)
        likeDb?.userDao()?.insert(item)
    }

    /** 찜 DB 에서 해당 내용 삭제 **/
    fun deleteLikeData(stayId: String) {
        val likeDb = LikeDb.getInstance(GoodChoiceApplication.instance)
        likeDb?.userDao()?.deleteLikeId(stayId)

//        val resultDeferred =
//            CoroutineScope(Dispatchers.IO).async {
//                withTimeout(5000L) {
//                    val likeDb = LikeDb.getInstance(GoodChoiceApplication.instance)
//                    likeDb?.userDao()?.deleteLikeId(stayId)
//                    true
//                }
//            }
//        return resultDeferred.await()
    }
}
package com.example.data.dataSource

import android.content.Context
import com.example.data.mapper.generateData
import com.example.data.remote.dto.StayItemDto
import com.example.data.remote.mock.S_1
import com.example.data.remote.mock.S_10
import com.example.data.remote.mock.S_11
import com.example.data.remote.mock.S_2
import com.example.data.remote.mock.S_3
import com.example.data.remote.mock.S_4
import com.example.data.remote.mock.S_5
import com.example.data.remote.mock.S_6
import com.example.data.remote.mock.S_7
import com.example.data.remote.mock.S_8
import com.example.data.remote.mock.S_9
import com.example.database.like.LikeDb
import com.example.database.like.LikeDbItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import javax.inject.Inject

class LikeDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getLikeData(): List<StayItemDto> {
        val list = CoroutineScope(Dispatchers.IO).async {
            val likeDb = LikeDb.getInstance(context)
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
        S_1,
        S_2,
        S_3,
        S_4,
        S_5,
        S_6,
        S_7,
        S_8,
        S_9,
        S_10,
        S_11
    )

    /** 찜 DB 에서 해당 내용이 있는지 여부 **/
    fun hasLikeData(stayItemId: String): Boolean {
        val likeDb = LikeDb.getInstance(context)
        val count = likeDb?.userDao()?.hasLike(stayItemId) ?: 0
        return count > 0
    }

    /** 찜 DB 에 내용 저장 **/
    fun insertLikeData(item: LikeDbItem) {
        val likeDb = LikeDb.getInstance(context)
        likeDb?.userDao()?.insert(item)
    }

    /** 찜 DB 에서 해당 내용 삭제 **/
    fun deleteLikeData(stayId: String) {
        val likeDb = LikeDb.getInstance(context)
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
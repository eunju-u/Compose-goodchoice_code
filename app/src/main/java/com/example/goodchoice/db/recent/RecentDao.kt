package com.example.goodchoice.db.recent

import androidx.room.*

@Dao
interface RecentDao {
    @Query("SELECT * FROM RecentDbItem")
    fun getAll(): List<RecentDbItem>

    @Query("SELECT COUNT(*) FROM RecentDbItem WHERE id = :id")
    fun isExistId(id:String) : Boolean

    @Query("DELETE FROM RecentDbItem WHERE id = :id")
    fun deleteRecentId(id: String)

    @Query("DELETE FROM RecentDbItem")
    fun deleteAll()

    @Insert
    fun insert(recent: RecentDbItem)

    @Update
    fun update(recent: RecentDbItem)

    @Delete
    fun delete(recent: RecentDbItem)

}
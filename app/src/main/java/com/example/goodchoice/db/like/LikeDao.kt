package com.example.goodchoice.db.like

import androidx.room.*

@Dao
interface LikeDao {
    @Query("SELECT * FROM LikeDbItem")
    fun getAll(): List<LikeDbItem>

    @Query("SELECT COUNT(*) FROM LikeDbItem WHERE id = :id")
    fun hasLike(id: String): Int

    @Query("DELETE FROM LikeDbItem WHERE id = :id")
    fun deleteLikeId(id: String)

    @Query("DELETE FROM LikeDbItem")
    fun deleteAll()

    @Insert
    fun insert(item: LikeDbItem)

    @Update
    fun update(item: LikeDbItem)

    @Delete
    fun delete(item: LikeDbItem)

}
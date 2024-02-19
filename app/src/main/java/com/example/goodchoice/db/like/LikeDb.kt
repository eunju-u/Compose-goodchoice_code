package com.example.goodchoice.db.like

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LikeDbItem::class], version = 1)
abstract class LikeDb : RoomDatabase() {
    abstract fun userDao(): LikeDao

    companion object {
        private var instance: LikeDb? = null

        @Synchronized
        fun getInstance(context: Context): LikeDb? {
            if (instance == null) {
                synchronized(LikeDb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LikeDb::class.java,
                        "LikeDb"
                    ).build()
                }
            }
            return instance
        }
    }
}
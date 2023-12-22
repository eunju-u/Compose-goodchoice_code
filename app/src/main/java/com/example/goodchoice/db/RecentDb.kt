package com.example.goodchoice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecentDbItem::class], version = 1)
abstract class RecentDb : RoomDatabase() {
    abstract fun userDao(): RecentDao

    companion object {
        private var instance: RecentDb? = null

        @Synchronized
        fun getInstance(context: Context): RecentDb? {
            if (instance == null) {
                synchronized(RecentDb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecentDb::class.java,
                        "user-database"
                    ).build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
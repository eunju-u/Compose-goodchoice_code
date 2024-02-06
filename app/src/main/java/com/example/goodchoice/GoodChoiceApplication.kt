package com.example.goodchoice

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GoodChoiceApplication : Application() {
    companion object {
        lateinit var instance: GoodChoiceApplication

        fun context() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
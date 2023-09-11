package com.example.goodchoice

import android.app.Application

class GoodChoiceApplication : Application() {
    companion object {
        lateinit var instance: GoodChoiceApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
package com.example.goodchoice.ui.myInfo.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.theme.TestTheme

class MyInfoDetailActivity : ComponentActivity() {
    companion object {
        val TAG: String = MyInfoDetailActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                MyInfoDetailContent(onFinish = {
                    this.finish()
                })
            }
        }
    }
}
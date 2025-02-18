package com.example.my_info.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ui_theme.*

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
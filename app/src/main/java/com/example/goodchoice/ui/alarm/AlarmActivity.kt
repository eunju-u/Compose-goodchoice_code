package com.example.goodchoice.ui.alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.goodchoice.ui.theme.TestTheme

class AlarmActivity : ComponentActivity() {
    companion object {
        val TAG: String = AlarmActivity::class.java.simpleName
    }
    private val viewModel: AlarmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                AlarmContent(onFinish = { this.finish() })
            }
        }
    }
}
package com.example.alarm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.intent.CommonIntent
import com.example.ui_theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmActivity : ComponentActivity() {
    companion object {
        val TAG: String = AlarmActivity::class.java.simpleName
    }

    private val viewModel: AlarmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                AlarmContent(viewModel = viewModel, onFinish = { this.finish() })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sendIntent(CommonIntent.LoadMyInfo)
    }
}
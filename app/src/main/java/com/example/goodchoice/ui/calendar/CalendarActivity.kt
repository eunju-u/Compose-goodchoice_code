package com.example.goodchoice.ui.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.goodchoice.ui.theme.TestTheme

class CalendarActivity : ComponentActivity() {
    companion object {
        val TAG: String = CalendarActivity::class.java.simpleName
    }

    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                CalendarContent(viewModel = viewModel, onFinish = { this.finish() })
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
package com.example.goodchoice.ui.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.Const
import com.example.common.theme.*
import com.example.goodchoice.ui.calendar.model.CalendarState

class CalendarActivity : ComponentActivity() {
    companion object {
        val TAG: String = CalendarActivity::class.java.simpleName
    }

    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getSerializableExtra(Const.TYPE)?.let {
            viewModel.type.value = it as CalendarType
        }
        //TODO 해외 캘린더인지 국내 캘린더인지 intent 받기
        intent.getStringExtra(Const.DATA)?.let {
            viewModel.isKoreaTravel = it == Const.KOREA
            viewModel.calendarState = CalendarState(it == Const.KOREA)
        }

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
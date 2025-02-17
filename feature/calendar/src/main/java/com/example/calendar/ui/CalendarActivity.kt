package com.example.calendar.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.calendar.ui.model.CalendarState
import com.example.common.CalendarType
import com.example.common.Const
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui_theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarActivity : ComponentActivity() {
    companion object {
        val TAG: String = CalendarActivity::class.java.simpleName
    }

    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = GoodChoicePreference(this)

        intent?.data?.getQueryParameter(Const.TYPE)?.let {
            viewModel.type.value = it.toCalendarTypeOrDefault()
        }

        //TODO 해외 캘린더인지 국내 캘린더인지 intent 받기
        intent?.data?.getQueryParameter(Const.DATA)?.let {
            viewModel.isKoreaTravel = it == Const.KOREA
            viewModel.calendarState = CalendarState(
                it == Const.KOREA,
                pref.koreaStartDate,
                pref.koreaEndDate,
                pref.overseaStartDate,
                pref.overseaEndDate
            )
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

    //string을 enum CalendarType 으로 형변환
    private fun String.toCalendarTypeOrDefault(default: CalendarType = CalendarType.CALENDAR): CalendarType {
        return CalendarType.values().find { it.name == this } ?: default
    }
}
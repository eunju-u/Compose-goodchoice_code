package com.example.goodchoice.ui.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.ui.calendar.model.CalendarState
import kotlinx.coroutines.launch
import java.time.LocalDate

enum class CalendarType {
    CALENDAR, PERSON
}

class CalendarViewModel : ViewModel() {

    //상단에 캘린더, 인원수 클릭시 type
    var type = mutableStateOf(CalendarType.CALENDAR)

    //국내 여행 캘린더 인지 해외 여행 캘린더 인지
    var isKoreaTravel = true

    var calendarState = CalendarState()

    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            calendarState.setSelectedDay(daySelected)
        }
    }
}
package com.example.goodchoice.ui.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.ui.calendar.model.KoreaCalendarState
import kotlinx.coroutines.launch
import java.time.LocalDate

enum class CalendarType {
    CALENDAR, PERSON
}
class CalendarViewModel : ViewModel() {
    val koreaCalendarState = KoreaCalendarState()

    //상단에 캘린더, 인원수 클릭시 type
    var type = mutableStateOf(CalendarType.CALENDAR)

    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            koreaCalendarState.setSelectedDay(daySelected)
        }
    }
}
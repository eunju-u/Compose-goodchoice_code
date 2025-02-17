package com.example.calendar.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendar.ui.model.CalendarState
import com.example.common.CalendarType
import com.example.data.local.preference.GoodChoicePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    //상단에 캘린더, 인원수 클릭시 type
    var type = mutableStateOf(CalendarType.CALENDAR)

    //국내 여행 캘린더 인지 해외 여행 캘린더 인지
    var isKoreaTravel = true

    val pref = GoodChoicePreference(context)
    var calendarState = CalendarState(
        koreaStartDate = pref.koreaStartDate,
        koreaEndDate = pref.koreaEndDate,
        overseaStartDate = pref.overseaStartDate,
        overseaEndDate = pref.overseaEndDate
    )

    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            calendarState.setSelectedDay(daySelected)
        }
    }
}
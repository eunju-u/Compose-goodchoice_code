package com.example.goodchoice.ui.calendar.model

import androidx.compose.runtime.mutableStateOf
import com.example.goodchoice.GoodChoiceApplication
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.calendar.widget.getNumberWeeks
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

class CalendarState(isKoreaCalendar: Boolean = true) {

    val pref = GoodChoicePreference(GoodChoiceApplication.context())

    val calendarUiState = mutableStateOf(
        CalendarUiState(
            selectedStartDate = if (isKoreaCalendar) LocalDate.parse(pref.koreaStartDate)
            else LocalDate.parse(pref.overseaStartDate),
            selectedEndDate = if (isKoreaCalendar) LocalDate.parse(pref.koreaEndDate)
            else LocalDate.parse(pref.overseaEndDate),
        )
    )
    val listMonths: List<Month>

    // 캘린더 시작하는 날짜
    private val calendarStartDate: LocalDate = LocalDate.now()

    // 현재날짜로 부터 5달 후 까지만 보여줌
    private val calendarEndDate: LocalDate = LocalDate.now().plusMonths(5)

    private val periodBetweenCalendarStartEnd: Period = Period.between(
        calendarStartDate,
        calendarEndDate
    )

    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(
                    Week(
                        number = week,
                        yearMonth = startYearMonth
                    )
                )
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()
    }

    fun setSelectedDay(newDate: LocalDate) {
        calendarUiState.value = updateSelectedDay(newDate)
    }

    private fun updateSelectedDay(newDate: LocalDate): CalendarUiState {
        val currentState = calendarUiState.value
        val selectedStartDate = currentState.selectedStartDate
        val selectedEndDate = currentState.selectedEndDate

        return when {
            selectedStartDate == null && selectedEndDate == null -> {
                currentState.setDates(newDate, null)
            }
            selectedStartDate != null && selectedEndDate != null -> {
                val animationDirection = if (newDate.isBefore(selectedStartDate)) {
                    AnimationDirection.BACKWARDS
                } else {
                    AnimationDirection.FORWARDS
                }
                this.calendarUiState.value = currentState.copy(
                    selectedStartDate = null,
                    selectedEndDate = null,
                    animateDirection = animationDirection
                )
                updateSelectedDay(newDate = newDate)
            }
            selectedStartDate == null -> {
                if (newDate.isBefore(selectedEndDate)) {
                    currentState.copy(animateDirection = AnimationDirection.BACKWARDS)
                        .setDates(newDate, selectedEndDate)
                } else if (newDate.isAfter(selectedEndDate)) {
                    currentState.copy(animateDirection = AnimationDirection.FORWARDS)
                        .setDates(selectedEndDate, newDate)
                } else {
                    currentState
                }
            }
            else -> {
                if (newDate.isBefore(selectedStartDate)) {
                    currentState.copy(animateDirection = AnimationDirection.BACKWARDS)
                        .setDates(newDate, selectedStartDate)
                } else if (newDate.isAfter(selectedStartDate)) {
                    currentState.copy(animateDirection = AnimationDirection.FORWARDS)
                        .setDates(selectedStartDate, newDate)
                } else {
                    currentState
                }
            }
        }
    }

    companion object {
        const val DAYS_IN_WEEK = 7
    }
}

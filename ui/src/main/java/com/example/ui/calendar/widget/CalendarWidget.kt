package com.example.ui.calendar.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.calendar.model.CalendarState
import com.example.ui.calendar.model.CalendarUiState
import com.example.ui.calendar.model.Month
import com.example.ui_theme.Theme
import com.example.ui_theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
fun CalendarWidget(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    calendarState: CalendarState,
    calendarUiState: CalendarUiState,
    onDayClicked : (LocalDate) ->Unit = {},
    selectedAnimationPercentage: () -> Float,
) {
    DaysOfWeek(modifier = modifier)
    HorizontalDivider(
        thickness = dp3, color = Theme.colorScheme.pureGray
    )
    LazyColumn(
        contentPadding = contentPadding,
        modifier = Modifier.padding(bottom = dp70),
    ) {
        calendarState.listMonths.forEachIndexed { idx, month ->
            itemsCalendarMonth(
                calendarUiState,
                onDayClicked,
                selectedAnimationPercentage,
                month,
                modifier
            )
            if(idx == calendarState.listMonths.lastIndex) {
                item {
                    Spacer(modifier = Modifier.padding(bottom = dp50))
                }
            }
        }
    }
}

private fun LazyListScope.itemsCalendarMonth(
    calendarUiState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit = {},
    selectedPercentageProvider: () -> Float,
    month: Month,
    weekModifier: Modifier = Modifier
) {
    item(month.yearMonth.month.name + month.yearMonth.year + "header") {
        MonthHeader(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp),
            month = month.yearMonth.month.name,
            year = month.yearMonth.year.toString()
        )
    }

    // A custom key needs to be given to these items so that they can be found in tests that
    // need scrolling. The format of the key is ${year/month/weekNumber}. Thus,
    // the key for the fourth week of December 2020 is "2020/12/4"
    itemsIndexed(month.weeks, key = { index, _ ->
        month.yearMonth.year.toString() + "/" + month.yearMonth.month.value + "/" + (index + 1).toString()
    }) { _, week ->
        val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
        // 일요일 부터 시작
        val currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        if (calendarUiState.hasSelectedPeriodOverlap(
                currentDay, currentDay.plusDays(6)
            )
        ) {
            //날짜 선택
            WeekSelectionPill(
                state = calendarUiState,
                currentWeekStart = currentDay,
                widthPerDay = CELL_SIZE,
                week = week,
                selectedPercentageTotalProvider = selectedPercentageProvider
            )
        }
        Week(
            calendarUiState = calendarUiState,
            modifier = weekModifier,
            week = week,
            onDayClicked = onDayClicked
        )
        Spacer(Modifier.height(8.dp))
    }
}
package com.example.goodchoice.ui.calendar.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.calendar.model.CalendarUiState
import com.example.goodchoice.ui.calendar.model.Week
import com.example.goodchoice.utils.ConvertUtil.convertDayOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
internal fun DaysOfWeek(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        // DayOfWeek.values() 는 요일을 반환하는데 시작 순서는 월요일부터이다.
        // 일요일 기준으로 가져오려면 재배열 해서 가져와야 한다.
        val days = DayOfWeek.values()

        // 배열을 재배열하여 일요일부터 시작하도록 변경
        val reorderedDays = arrayOfNulls<DayOfWeek>(7)
        for (i in 0..6) {
            reorderedDays[i] = days[(i + 6) % 7] // 6을 더한 후 7로 나누어 순환시킴
        }

        for (day in reorderedDays) {
            DayOfWeekHeading(
                day = stringResource(id = convertDayOfWeek(day?.name ?: ""))
            )
        }
    }
}

@Composable
internal fun Week(
    calendarUiState: CalendarUiState,
    week: Week,
    onDayClicked: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
    var currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val sunday = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val today = LocalDate.now()
    Box {
        Row(modifier = modifier) {
            Spacer(
                Modifier
                    .weight(1f)
                    .heightIn(max = CELL_SIZE)
            )
            for (i in 0..6) {
                if (currentDay.month == week.yearMonth.month) {
                    Day(
                        calendarState = calendarUiState,
                        day = currentDay,
                        today = today,
                        sunday = sunday,
                        onClickEnabled = currentDay >= today,
                        onDayClicked = onDayClicked,
                    )
                } else {
                    Box(modifier = Modifier.size(CELL_SIZE))
                }
                currentDay = currentDay.plusDays(1)
            }
            Spacer(
                Modifier
                    .weight(1f)
                    .heightIn(max = CELL_SIZE)
            )
        }
    }
}

internal val CELL_SIZE = 48.dp

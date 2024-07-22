package com.example.goodchoice.ui.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.goodchoice.R
import java.time.LocalDate
import com.example.goodchoice.ui.calendar.model.CalendarState
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.ui.theme.Theme

@Composable
fun CalendarContent(
    viewModel: CalendarViewModel,
    onFinish: () -> Unit = {}
) {
    val calendarState = remember { viewModel.calendarState }
    val type = remember { viewModel.type }

    CalendarContent(
        isKoreaTravel = viewModel.isKoreaTravel,
        type = type,
        calendarState = calendarState,
        onDayClicked = { dateClicked ->
            viewModel.onDaySelected(dateClicked)
        },
        onFinish = onFinish
    )
}

@Composable
private fun CalendarContent(
    isKoreaTravel: Boolean = true,
    type: MutableState<CalendarType> = mutableStateOf(CalendarType.CALENDAR),
    calendarState: CalendarState,
    onDayClicked: (LocalDate) -> Unit = {},
    onFinish: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        containerColor = Theme.colorScheme.white,
        contentColor = Theme.colorScheme.darkGray,
        topBar = {
            val date = stringResource(id = R.string.str_date)
            val person = stringResource(id = R.string.str_person)
            val schedule = stringResource(id = R.string.str_schedule)
            val personAndStay = stringResource(id = R.string.str_person_and_guest_room)
            val title = if (isKoreaTravel) {
                stringResource(
                    id = R.string.str_select,
                    if (type.value == CalendarType.CALENDAR) date else person
                )
            } else {
                if (type.value == CalendarType.CALENDAR)
                    stringResource(id = R.string.str_select, schedule)
                else personAndStay
            }
            TopAppBarWidget(
                title = title,
                onFinish = { onFinish() },
                isCloseButton = true
            )
//            CalendarTopAppBar(calendarState, onBackPressed)
        }
    ) { contentPadding ->
        Calendar(
            isKoreaTravel = isKoreaTravel,
            overseaStayList = listOf(2, 4, 7, 14),
            calendarType = type,
            calendarState = calendarState,
            onDayClicked = onDayClicked,
            contentPadding = contentPadding
        )
    }
}

//    val state = rememberCalendarState(
//        startMonth = YearMonth.now(),
//        endMonth = YearMonth.now().plusMonths(5)
//    )
//
//    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        TopAppBarWidget(
//            title = stringResource(id = R.string.str_select_date),
//            onFinish = { onFinish() })
//        VerticalCalendar(
//            state = state,
//            monthHeader = {
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Text(
//                        modifier = Modifier.weight(1f),
//                        textAlign = TextAlign.Center,
//                        text = state.startMonth.toString()
//                    )
//                }
//            },
//            monthContainer = { _, container ->
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                ) {
//                    container()
//                }
//            },
//            dayContent = { day ->
//                Day(day, isSelected = selectedDate == day.date) { day ->
//                    selectedDate = if (selectedDate == day.date) null else day.date
//                }
//            }
//        )
//
//        Row(modifier = Modifier.fillMaxWidth()) {
//
//        }
//    }
//}
//
//@Composable
//fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
//    Box(
//        modifier = Modifier
//            .aspectRatio(1f)
//            .clip(CircleShape)
//            .background(color = if (isSelected) Color.Green else Color.Transparent)
//            .clickable(
//                enabled = day.position == DayPosition.MonthDate,
//                onClick = { onClick(day) }
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = day.date.dayOfMonth.toString())
//    }
//}
package com.example.goodchoice.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import java.time.LocalDate
import com.example.goodchoice.ui.calendar.model.KoreaCalendarState
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.ui.theme.Theme

@Composable
fun CalendarContent(
    viewModel: CalendarViewModel,
    onFinish: () -> Unit = {}
) {
    val calendarState = remember { viewModel.koreaCalendarState }
    val type = remember { viewModel.type }

    CalendarContent(
        type = type,
        koreaCalendarState = calendarState,
        onDayClicked = { dateClicked ->
            viewModel.onDaySelected(dateClicked)
        },
        onFinish = onFinish
    )
}

@Composable
private fun CalendarContent(
    type: MutableState<CalendarType> = mutableStateOf(CalendarType.CALENDAR),
    koreaCalendarState: KoreaCalendarState,
    onDayClicked: (LocalDate) -> Unit = {},
    onFinish: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        backgroundColor = Theme.colorScheme.white,
        contentColor = Theme.colorScheme.darkGray,
        topBar = {
            val date = stringResource(id = R.string.str_date)
            val person = stringResource(id = R.string.str_person)
            TopAppBarWidget(
                title = stringResource(
                    id = R.string.str_select,
                    if (type.value == CalendarType.CALENDAR) date else person
                ),
                onFinish = { onFinish() },
                isCloseButton = true
            )
//            CalendarTopAppBar(calendarState, onBackPressed)
        }
    ) { contentPadding ->
        Calendar(
            calendarType = type,
            koreaCalendarState = koreaCalendarState,
            onDayClicked = onDayClicked,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun CalendarTopAppBar(koreaCalendarState: KoreaCalendarState, onBackPressed: () -> Unit) {
    val calendarUiState = koreaCalendarState.koreaCalendarUiState.value
    Column {
        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        )
        TopAppBar(
            title = {
                Text(
                    text = if (!calendarUiState.hasSelectedDates) {
                        "Select Dates"
                    } else {
                        calendarUiState.selectedDatesFormatted
                    }
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primaryVariant,
            elevation = 0.dp
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
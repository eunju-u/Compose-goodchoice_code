package com.example.goodchoice.ui.calendar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.CalendarPersonType
import com.example.goodchoice.R
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.calendar.model.CalendarState
import com.example.goodchoice.ui.calendar.model.CalendarUiState
import com.example.goodchoice.ui.calendar.model.Month
import com.example.goodchoice.ui.calendar.widget.*
import com.example.goodchoice.ui.calendar.widget.CELL_SIZE
import com.example.goodchoice.ui.calendar.widget.DaysOfWeek
import com.example.goodchoice.ui.calendar.widget.Week
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    isKoreaTravel: Boolean = true,
    calendarType: MutableState<CalendarType> = mutableStateOf(CalendarType.CALENDAR),
    calendarState: CalendarState,
    onDayClicked: (date: LocalDate) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)

    val type = calendarType.value
    val calendarUiState = calendarState.calendarUiState.value
    val numberSelectedDays = calendarUiState.numberSelectedDays.toInt()
    //국내 여행 인원수
    var koreaPersonCount by remember { mutableStateOf(pref.koreaPersonCount) }
    //해외 여행 객실 수
    var overSeaStayCount by remember { mutableStateOf(pref.overseaStayCount) }
    //해외 여행 성인 수
    var overseaAdultCount by remember { mutableStateOf(pref.overseaAdultCount) }
    //해외 여행 아동 수
    var overseaKidCount by remember { mutableStateOf(pref.overseaKidCount) }

    val selectedAnimationPercentage = remember(numberSelectedDays) {
        Animatable(0f)
    }
    // Start a Launch Effect when the number of selected days change.
    // using .animateTo() we animate the percentage selection from 0f - 1f
    LaunchedEffect(numberSelectedDays) {
        if (calendarUiState.hasSelectedDates) {

            val animationSpec: TweenSpec<Float> = tween(
                durationMillis = (numberSelectedDays.coerceAtLeast(0) * DURATION_MILLIS_PER_DAY).coerceAtMost(
                    2000
                ), easing = EaseOutQuart
            )
            selectedAnimationPercentage.animateTo(
                targetValue = 1f, animationSpec = animationSpec
            )
        }
    }

    val calendarBorderColor =
        if (type == CalendarType.CALENDAR) Theme.colorScheme.blue else Theme.colorScheme.pureGray
    val personBorderColor =
        if (type == CalendarType.PERSON) Theme.colorScheme.blue else Theme.colorScheme.pureGray

    val calendarContentColor =
        if (type == CalendarType.CALENDAR) Theme.colorScheme.blue else Theme.colorScheme.gray
    val personContentColor =
        if (type == CalendarType.PERSON) Theme.colorScheme.blue else Theme.colorScheme.gray

    val weekModifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
    Box(modifier = modifier.padding(contentPadding)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dp20, end = dp20),
                horizontalArrangement = Arrangement.spacedBy(dp10)
            ) {
                val startCalendarDate =
                    if (isKoreaTravel) pref.koreaStartDate else pref.overseaStartDate
                val endCalendarDate = if (isKoreaTravel) pref.koreaEndDate else pref.overseaEndDate
                val startDate =
                    (calendarUiState.selectedStartDate ?: startCalendarDate).toString()
                //select 한 start 가 없으면 오늘 날짜로 start가 되고 end 는 오늘 날짜 다음 날짜가 된다.
                //select 한 start 가 있으면 end 은 빈값이 되어야 한다.
                val firstEndData =
                    if (calendarUiState.selectedStartDate == LocalDate.parse(startCalendarDate)) endCalendarDate else ""
                val endDate = (calendarUiState.selectedEndDate ?: firstEndData).toString()
                val startDateFormat = ConvertUtil.formatDate(startDate)
                val endDateFormat = ConvertUtil.formatDate(endDate)
                val startTitle =
                    if (startDate.isNotEmpty())
                        "$startDateFormat " + stringResource(
                            id = ConvertUtil.convertDayOfWeek(
                                LocalDate.parse(startDate).dayOfWeek.name
                            )
                        ) else ""
                val endTitle =
                    if (endDate.isNotEmpty())
                        "$endDateFormat " + stringResource(
                            id = ConvertUtil.convertDayOfWeek(
                                LocalDate.parse(endDate).dayOfWeek.name
                            )
                        ) else ""

                LeftImageButtonWidget(
                    modifier = Modifier.weight(1f),
                    title = "$startTitle - $endTitle",
                    borderColor = calendarBorderColor,
                    contentColor = calendarContentColor,
                    shape = dp20,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    innerPadding = PaddingValues(horizontal = dp30, vertical = dp15),
                    content = {
                        Image(
                            modifier = Modifier.size(dp15),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            colorFilter = ColorFilter.tint(calendarContentColor),
                            contentDescription = null
                        )
                    },
                    onItemClick = { calendarType.value = CalendarType.CALENDAR }
                )
                //국내 여행 캘린더
                if (isKoreaTravel) {
                    val person =
                        if (koreaPersonCount == 10) "$koreaPersonCount+" else koreaPersonCount.toString()
                    LeftImageButtonWidget(
                        title = stringResource(id = R.string.str_person_count, person),
                        borderColor = personBorderColor,
                        contentColor = personContentColor,
                        shape = dp20,
                        style = MaterialTheme.typography.labelLarge,
                        innerPadding = PaddingValues(horizontal = dp30, vertical = dp15),
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_nav_my_page),
                                colorFilter = ColorFilter.tint(personContentColor),
                                contentDescription = null
                            )
                        },
                        onItemClick = { calendarType.value = CalendarType.PERSON }
                    )
                }
                //해외 여행 캘린더
                else {
                    val count = stringResource(id = R.string.str_count)
                    val perPerson = stringResource(id = R.string.str_per_person)
                    PersonButtonWidget(
                        borderColor = personBorderColor,
                        contentColor = personContentColor,
                        leftText = "$overSeaStayCount $count",
                        rightText = "${overseaAdultCount + overseaKidCount} $perPerson",
                        onItemClick = { calendarType.value = CalendarType.PERSON }
                    )
                }
            }

            if (type == CalendarType.CALENDAR) {
                DaysOfWeek(modifier = weekModifier)
                Divider(
                    thickness = dp3, color = Theme.colorScheme.pureGray
                )
                LazyColumn(
                    contentPadding = contentPadding,
                    modifier = Modifier.padding(bottom = dp70),
                ) {
                    calendarState.listMonths.forEach { month ->
                        itemsCalendarMonth(
                            calendarUiState,
                            onDayClicked,
                            { selectedAnimationPercentage.value },
                            month,
                            weekModifier
                        )
                    }

                    item(key = "bottomSpacer") {
                        Spacer(
                            modifier = Modifier.windowInsetsBottomHeight(
                                WindowInsets.navigationBars
                            )
                        )
                    }
                }
            } else {
                if (isKoreaTravel) {
                    KoreaPersonWidget(
                        contentPadding = contentPadding,
                        personCount = koreaPersonCount,
                        onLeftItemClick = { if (koreaPersonCount > 1) koreaPersonCount-- },
                        onRightItemClick = { if (koreaPersonCount < 10) koreaPersonCount++ }
                    )
                } else {
                    OverseaPersonWidget(
                        contentPadding = contentPadding,
                        stayCount = overSeaStayCount,
                        adultCount = overseaAdultCount,
                        kidCount = overseaKidCount,
                        onLeftItemClick = { type ->
                            when (type) {
                                CalendarPersonType.GUEST_ROOM -> {
                                    if (overSeaStayCount > 1) overseaAdultCount--
                                }
                                CalendarPersonType.ADULT -> {
                                    if (overseaAdultCount > 1) overseaAdultCount--
                                }
                                CalendarPersonType.KID -> {
                                    if (overseaKidCount > 0) overseaKidCount--
                                }
                            }
                        },
                        onRightItemClick = { type ->
                            when (type) {
                                CalendarPersonType.GUEST_ROOM -> {
                                    if (overSeaStayCount < 9) overSeaStayCount++
                                }
                                CalendarPersonType.ADULT -> {
                                    if (overseaAdultCount < 36) overseaAdultCount++
                                }
                                CalendarPersonType.KID -> {
                                    if (overseaKidCount < 9) overseaKidCount++
                                }
                            }
                        }
                    )
                }
            }
        }

        CardWidget(modifier = Modifier.align(Alignment.BottomEnd),
            isVisibleShadow = true,
            shadowOffsetY = -dp5,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dp50)
                ) {

                    LeftImageButtonWidget(modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight(),
                        title = stringResource(id = R.string.str_reset),
                        innerPadding = PaddingValues(horizontal = dp5),
                        shape = dp0,
                        contentColor = Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelMedium,
                        endPadding = dp10,
                        onItemClick = {},
                        content = {
                            Icon(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_refresh),
                                tint = Theme.colorScheme.gray,
                                contentDescription = null
                            )
                        })

                    //적용 버튼
                    ButtonWidget(modifier = Modifier.weight(0.7f),
                        containerColor = Theme.colorScheme.blue,
                        content = {
                            val days =
                                if (calendarUiState.numberSelectedDays.toInt() - 1 <= 0) 1 else calendarUiState.numberSelectedDays.toInt() - 1

                            Text(
                                text = stringResource(id = R.string.str_day_apply, days),
                                style = MaterialTheme.typography.labelLarge,
                                color = Theme.colorScheme.white
                            )
                        },
                        onItemClick = {
                            if (isKoreaTravel) {
                                pref.koreaPersonCount = koreaPersonCount
                                pref.koreaStartDate = calendarUiState.selectedStartDate.toString()
                                pref.koreaEndDate = if (calendarUiState.selectedEndDate != null) {
                                    calendarUiState.selectedEndDate.toString()
                                } else {
                                    calendarUiState.selectedStartDate?.plusDays(1).toString()
                                }
                            } else {
                                pref.overseaStartDate = calendarUiState.selectedStartDate.toString()
                                pref.overseaEndDate = if (calendarUiState.selectedEndDate != null) {
                                    calendarUiState.selectedEndDate.toString()
                                } else {
                                    calendarUiState.selectedStartDate?.plusDays(1).toString()
                                }
                                pref.overseaStayCount = overSeaStayCount
                                pref.overseaAdultCount = overseaAdultCount
                                pref.overseaKidCount = overseaKidCount
                            }
                            (context as CalendarActivity).finish()
                        })
                }
            })
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

internal val CALENDAR_STARTS_ON = WeekFields.ISO

@Preview
@Composable
fun PreviewCalendar() {
    TestTheme {
        Calendar(calendarState = CalendarState(), onDayClicked = { })
    }
}

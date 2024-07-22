package com.example.goodchoice.ui.calendar.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import com.example.goodchoice.ui.calendar.model.CalendarUiState
import com.example.goodchoice.ui.components.ShapeButton
import com.example.goodchoice.ui.theme.*
import java.time.LocalDate

@Composable
internal fun DayOfWeekHeading(day: String) {
    DayContainer {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            text = day,
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = { },
    onClickEnabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    onClickLabel: String? = null,
    isToday: Boolean = false,
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel = if (selected) "Selected" else "Not selected"

    Box(
        modifier = modifier
            .size(width = CELL_SIZE, height = CELL_SIZE)
            .pointerInput(Any()) {
                detectTapGestures {
                    onClick()
                }
            }
            .then(
                if (onClickEnabled) {
                    modifier.semantics {
                        stateDescription = stateDescriptionLabel
                        onClick(label = onClickLabel, action = null)
                    }
                } else {
                    modifier.clearAndSetSemantics { }
                }
            )
            .background(backgroundColor)
    ) {
        content()
        if (isToday) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = dp8)
            ) {
                ShapeButton(size = dp6, normalColor = Theme.colorScheme.blue)
            }
        }
    }
}

@Composable
internal fun Day(
    modifier: Modifier = Modifier,
    day: LocalDate,
    today: LocalDate,
    sunday: LocalDate,
    onClickEnabled: Boolean = true,
    calendarState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
) {
    val selected = calendarState.isDateInSelectedPeriod(day)
    DayContainer(
        modifier = modifier,
        selected = selected,
        onClickEnabled = onClickEnabled,
        onClick = {
            if (onClickEnabled) {
                onDayClicked(day)
            }
        },
        onClickLabel = "select",
        isToday = day == today,
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            text = day.dayOfMonth.toString(),
            color = if (!onClickEnabled) Theme.colorScheme.pureGray else if (sunday == day) Theme.colorScheme.red else Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

val DayStatusKey = SemanticsPropertyKey<Boolean>("DayStatusKey")
var SemanticsPropertyReceiver.dayStatusProperty by DayStatusKey

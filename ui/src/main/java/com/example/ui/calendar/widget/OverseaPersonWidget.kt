package com.example.ui.calendar.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.common.CalendarPersonType
import com.example.ui_common.R
import com.example.ui_theme.*

/**
 * 해외 캘린더 인원
 */
@Composable
fun OverseaPersonWidget(
    contentPadding: PaddingValues = PaddingValues(dp0),
    stayCount: Int = 0,
    adultCount: Int = 0,
    kidCount: Int = 0,
    onLeftItemClick: (type: CalendarPersonType) -> Unit = {},
    onRightItemClick: (type: CalendarPersonType) -> Unit = {}
) {
    //객실 최대수
    val maxStay = 9
    //성인 최대수
    val maxAdult = 36
    //아동 최대수
    val maxKid = 9
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(top = dp20, start = dp25, end = dp25)
    ) {
        item {
            PersonWidget(
                title = stringResource(id = R.string.str_guest_room),
                count = stayCount, max = maxStay,
                onLeftItemClick = {
                    onLeftItemClick(CalendarPersonType.GUEST_ROOM)
                }, onRightItemClick = {
                    onRightItemClick(CalendarPersonType.GUEST_ROOM)
                }
            )

            PersonWidget(
                title = stringResource(id = R.string.str_adult),
                description = stringResource(id = R.string.str_guest_room_one_person_select),
                count = adultCount, max = maxAdult,
                onLeftItemClick = {
                    onLeftItemClick(CalendarPersonType.ADULT)
                }, onRightItemClick = {
                    onRightItemClick(CalendarPersonType.ADULT)
                }
            )

            PersonWidget(
                title = stringResource(id = R.string.str_kid),
                count = kidCount, max = maxKid, isKid = true,
                onLeftItemClick = {
                    onLeftItemClick(CalendarPersonType.KID)
                }, onRightItemClick = {
                    onRightItemClick(CalendarPersonType.KID)
                }
            )
        }
    }
}
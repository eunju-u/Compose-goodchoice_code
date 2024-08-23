package com.example.goodchoice.ui.around.widget

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.common.RoomType
import com.example.ui.R
import com.example.ui.components.*
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui.calendar.CalendarActivity
import com.example.ui.theme.Theme
import com.example.ui.theme.*
import com.example.ui.utils.ConvertUtil

@Composable
fun AroundTopWidget(
    selectedRoomType: RoomType = RoomType.SLEEP_ROOM, //숙박 인지 대실인지
    onRoomTypeClick: (roomType: RoomType) -> Unit = {}, //선택한 숙박/대실 상위로 보내기 위한 콜백
    selectedSearchText: String = "", //검색한 text
    onSearchClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)

    // 숙박
    val isSleepRoom = selectedRoomType == RoomType.SLEEP_ROOM
    // 대실
    val isRentalRoom = selectedRoomType == RoomType.RENTAL_ROOM

    val localAndStaySearch =
        selectedSearchText.ifEmpty { stringResource(id = R.string.str_search_local_stay) }

    CardWidget(
        modifier = Modifier.fillMaxWidth(),
        isVisibleShadow = true,
        innerPadding = PaddingValues(vertical = dp20, horizontal = dp20),
        containerColor = Theme.colorScheme.white,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dp15),
                    horizontalArrangement = Arrangement.spacedBy(dp10)
                ) {
                    ImageButtonWidget(
                        modifier = Modifier.weight(1f),
                        isCenterHorizontalArrangement = false,
                        containerColor = Theme.colorScheme.pureGray,
                        contentColor = Theme.colorScheme.darkGray,
                        title = localAndStaySearch,
                        style = MaterialTheme.typography.labelMedium,
                        onItemClick = {
                            onSearchClick()
                        },
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_map),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                contentDescription = null
                            )
                        })
                    ImageButtonWidget(
                        isCenterHorizontalArrangement = false,
                        containerColor = Theme.colorScheme.pureGray,
                        contentColor = Theme.colorScheme.darkGray,
                        title = ConvertUtil.formatDate(pref.koreaStartDate) +
                                " - ${ConvertUtil.formatDate(pref.koreaEndDate)}" +
                                " / ${
                                    stringResource(
                                        id = R.string.str_person_count,
                                        pref.koreaPersonCount
                                    )
                                }",
                        style = MaterialTheme.typography.labelMedium,
                        onItemClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    CalendarActivity::class.java
                                )
                            )
                        },
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_calendar),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                contentDescription = null
                            )
                        })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dp10)
                ) {
                    ImageButtonWidget(
                        isCenterHorizontalArrangement = false,
                        contentColor = if (isSleepRoom) Theme.colorScheme.white else Theme.colorScheme.darkGray,
                        containerColor = if (isSleepRoom) Theme.colorScheme.blue else Color.Transparent,
                        borderColor = if (isSleepRoom) Color.Transparent else Theme.colorScheme.pureGray,
                        shape = dp30,
                        title = stringResource(id = R.string.str_sleep_room),
                        style = MaterialTheme.typography.labelMedium,
                        onItemClick = { onRoomTypeClick(RoomType.SLEEP_ROOM) },
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_bed),
                                colorFilter = ColorFilter.tint(if (isSleepRoom) Theme.colorScheme.white else Theme.colorScheme.gray),
                                contentDescription = null
                            )
                        })
                    ImageButtonWidget(
                        isCenterHorizontalArrangement = false,
                        contentColor = if (isRentalRoom) Theme.colorScheme.white else Theme.colorScheme.darkGray,
                        containerColor = if (isRentalRoom) Theme.colorScheme.blue else Color.Transparent,
                        borderColor = if (isRentalRoom) Color.Transparent else Theme.colorScheme.pureGray,
                        shape = dp30,
                        title = stringResource(id = R.string.str_rental_room),
                        style = MaterialTheme.typography.labelMedium,
                        onItemClick = { onRoomTypeClick(RoomType.RENTAL_ROOM) },
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_time),
                                colorFilter = ColorFilter.tint(if (isRentalRoom) Theme.colorScheme.white else Theme.colorScheme.gray),
                                contentDescription = null
                            )
                        })
                }
            }
        })
}
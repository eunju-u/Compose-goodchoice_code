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
import com.example.goodchoice.R
import com.example.goodchoice.RoomType
import com.example.goodchoice.ui.calendar.CalendarActivity
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.ImageButtonWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun AroundTopWidget(
    selectedRoomType: RoomType = RoomType.SLEEP_ROOM,
    onItemClick: (roomType: RoomType) -> Unit = {}
) {
    val context = LocalContext.current
    // 숙박
    val isSleepRoom = selectedRoomType == RoomType.SLEEP_ROOM
    // 대실
    val isRentalRoom = selectedRoomType == RoomType.RENTAL_ROOM

    CardWidget(
        modifier = Modifier.fillMaxWidth(),
        isVisibleShadow = true,
        innerPadding = PaddingValues(vertical = dp20, horizontal = dp20),
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
                        title = stringResource(id = R.string.str_search_local_stay),
                        style = MaterialTheme.typography.labelMedium,
                        onItemClick = {},
                        content = {
                            Image(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_map),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                contentDescription = null
                            )
                        })
                    ImageButtonWidget(
                        modifier = Modifier.weight(1f),
                        isCenterHorizontalArrangement = false,
                        containerColor = Theme.colorScheme.pureGray,
                        contentColor = Theme.colorScheme.darkGray,
                        title = "",
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
                        onItemClick = { onItemClick(RoomType.SLEEP_ROOM) },
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
                        onItemClick = { onItemClick(RoomType.RENTAL_ROOM) },
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
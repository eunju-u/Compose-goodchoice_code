package com.example.goodchoice.ui.search

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.ui.calendar.CalendarActivity
import com.example.goodchoice.ui.calendar.CalendarType
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun OverSeaContent(
    modifier: Modifier = Modifier,
    date: String = "",
    personCount: Int = 2,
    onItemClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val innerPadding = PaddingValues(horizontal = dp20, vertical = dp15)

    Column(
        modifier = modifier
    ) {
        LeftImageButtonWidget(
            modifier = Modifier.fillMaxWidth(),
            outerPadding = PaddingValues(top = dp20, start = dp20, bottom = dp15, end = dp20),
            innerPadding = innerPadding,
            isCenterHorizontalArrangement = false,
            title = stringResource(id = R.string.str_search_oversea_area),
            containerColor = Theme.colorScheme.pureGray,
            contentColor = Theme.colorScheme.gray,
            shape = dp10,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            content = {
                Image(
                    modifier = Modifier.size(dp15),
                    painter = painterResource(id = R.drawable.ic_nav_search),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                    contentDescription = null
                )
            },
            onItemClick = { }
        )
        LeftImageButtonWidget(
            modifier = Modifier.fillMaxWidth(),
            outerPadding = PaddingValues(start = dp20, bottom = dp15, end = dp20),
            innerPadding = innerPadding,
            title = date,
            isCenterHorizontalArrangement = false,
            containerColor = Theme.colorScheme.pureGray,
            contentColor = Theme.colorScheme.darkGray,
            shape = dp10,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            content = {
                Image(
                    modifier = Modifier.size(dp15),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                    contentDescription = null
                )
            },
            onItemClick = {
                context.startActivity(
                    Intent(
                        context,
                        CalendarActivity::class.java
                    ).apply {
                        putExtra(Const.TYPE, CalendarType.CALENDAR)
                        putExtra(Const.DATA, Const.OVERSEA)
                    }
                )
            }
        )
        LeftImageButtonWidget(
            modifier = Modifier.fillMaxWidth(),
            outerPadding = PaddingValues(start = dp20, bottom = dp15, end = dp20),
            innerPadding = innerPadding,
            isCenterHorizontalArrangement = false,
            title = stringResource(
                id = R.string.str_person_count,
                personCount
            ),
            containerColor = Theme.colorScheme.pureGray,
            contentColor = Theme.colorScheme.darkGray,
            shape = dp10,
            style = MaterialTheme.typography.labelLarge,
            content = {
                Image(
                    modifier = Modifier.size(dp15),
                    painter = painterResource(id = R.drawable.ic_nav_my_page),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                    contentDescription = null
                )
            }
        )

        ButtonWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dp20, end = dp20),
            containerColor = Theme.colorScheme.blue,
            contentColor = Theme.colorScheme.white,
            content = {
                Text(
                    text = stringResource(id = R.string.navi_search),
                    style = MaterialTheme.typography.labelLarge
                )
            })
    }
}
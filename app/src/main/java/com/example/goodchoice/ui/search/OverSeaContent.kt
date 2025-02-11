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
import com.example.common.Const
import com.example.ui.R
import com.example.ui.components.*
import com.example.ui_theme.*
import com.example.ui.calendar.CalendarActivity
import com.example.ui.calendar.CalendarType

@Composable
fun OverSeaContent(
    modifier: Modifier = Modifier,
    date: String = "",
    text: String = "",
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
            title = text,
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
            },
            onItemClick = {
                context.startActivity(
                    Intent(
                        context,
                        CalendarActivity::class.java
                    ).apply {
                        putExtra(Const.TYPE, CalendarType.PERSON)
                        putExtra(Const.DATA, Const.OVERSEA)
                    }
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
package com.example.goodchoice.ui.search

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.common.Const
import com.example.ui.R
import com.example.ui.components.KoreaDateWidget
import com.example.ui.components.LeftImageButtonWidget
import com.example.ui.components.RowTwoWidget
import com.example.ui_theme.*
import com.example.domain.model.FilterItem
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui.calendar.CalendarActivity
import com.example.ui.calendar.CalendarType

@Composable
fun KoreaStayContent(
    modifier: Modifier = Modifier,
    rankList: List<FilterItem> = emptyList(),
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val innerPadding = PaddingValues(horizontal = dp15, vertical = dp15)

    LazyColumn(
        modifier = modifier
    ) {
        item {
            LeftImageButtonWidget(
                modifier = Modifier.fillMaxWidth(),
                outerPadding = PaddingValues(top = dp30, start = dp20, end = dp20),
                innerPadding = innerPadding,
                isCenterHorizontalArrangement = false,
                title = stringResource(id = R.string.str_search_korea_area),
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

            KoreaDateWidget(
                koreaPersonCount = pref.koreaPersonCount,
                startDate = pref.koreaStartDate,
                endDate = pref.koreaEndDate,
                onLeftItemClick = {
                    context.startActivity(
                        Intent(
                            context,
                            CalendarActivity::class.java
                        ).apply {
                            putExtra(Const.TYPE, CalendarType.CALENDAR)
                        }
                    )
                },
                onRightItemClick = {
                    context.startActivity(
                        Intent(
                            context,
                            CalendarActivity::class.java
                        ).apply {
                            putExtra(Const.TYPE, CalendarType.PERSON)
                        }
                    )
                }
            )

            LeftImageButtonWidget(
                title = stringResource(id = R.string.str_search_my_arround),
                outerPadding = PaddingValues(top = dp25, start = dp20, end = dp20, bottom = dp20),
                contentColor = Theme.colorScheme.blue,
                borderColor = Theme.colorScheme.blue,
                shape = dp20,
                style = MaterialTheme.typography.labelMedium,
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_send_message),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.blue),
                        contentDescription = null
                    )
                }
            )

            Text(
                modifier = Modifier.padding(top = dp15, start = dp20, end = dp20, bottom = dp15),
                text = stringResource(id = R.string.str_search_rank),
                style = MaterialTheme.typography.labelLarge,
                color = Theme.colorScheme.darkGray
            )
        }

        itemsIndexed(items = rankList) { idx, item ->
            RowTwoWidget(
                modifier = Modifier.fillMaxWidth(),
                innerPadding = PaddingValues(top = dp10, bottom = dp10, start = dp20, end = dp20),
                leftModifier = Modifier.width(dp25),
                leftText = (idx + 1).toString(),
                rightText = item.filterTitle,
                leftStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                rightStyle = MaterialTheme.typography.labelLarge,
                endPadding = dp20,
                onItemClick = {}
            )
        }
    }
}
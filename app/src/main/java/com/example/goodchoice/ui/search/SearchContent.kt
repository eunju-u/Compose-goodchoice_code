package com.example.goodchoice.ui.search

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.Const
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.TabData
import com.example.goodchoice.ui.calendar.CalendarActivity
import com.example.goodchoice.ui.calendar.CalendarType
import com.example.goodchoice.ui.components.TabWidget
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.ConvertUtil
import java.time.LocalDate

private val menus = listOf(
    TabData.KOREA_STAY, TabData.OVERSEA_STAY, TabData.LEISURE
)

/**
 * 검색 화면
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier, viewModel: MainViewModel
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0)

    val leisureSearchWordData = viewModel.leisureSearchWordData

    val pageModifier = Modifier
        .fillMaxSize()
        .background(color = Theme.colorScheme.white)

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TabWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dp15, end = dp15),
                menus = menus,
                pagerState = pagerState,
                selectedContentColor = Theme.colorScheme.blue,
                unselectedContentColor = Theme.colorScheme.gray,
                divider = { Divider(color = Theme.colorScheme.pureGray) }
            ) { state ->
                HorizontalPager(
                    modifier = pageModifier,
                    pageCount = menus.size,
                    state = state,
                    verticalAlignment = Alignment.Top,
                    userScrollEnabled = false
                ) { page: Int ->
                    if (homeUiState.value is ConnectInfo.Available) {
                        when (menus[page].route) {
                            TabData.KOREA_STAY.route -> {
                                val startDate = pref.startDate
                                val endDate = pref.endDate

                                val startDateFormat = ConvertUtil.formatDate(startDate)
                                val endDateFormat = ConvertUtil.formatDate(endDate)
                                val startDayOfWeek =
                                    ConvertUtil.convertDayOfWeek(LocalDate.parse(startDate).dayOfWeek.name)
                                val endDayOfWeek =
                                    ConvertUtil.convertDayOfWeek(LocalDate.parse(endDate).dayOfWeek.name)
                                val date =
                                    "$startDateFormat ${stringResource(id = startDayOfWeek)} " +
                                            "- $endDateFormat ${stringResource(id = endDayOfWeek)}"

                                KoreaStayContent(modifier = pageModifier,
                                    date = date,
                                    personCount = pref.personCount,
                                    onLeftItemClick = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                CalendarActivity::class.java
                                            ).apply {
                                                putExtra(Const.DATA, CalendarType.CALENDAR)
                                            }
                                        )
                                    },
                                    onRightItemClick = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                CalendarActivity::class.java
                                            ).apply {
                                                putExtra(Const.DATA, CalendarType.PERSON)
                                            }
                                        )
                                    })
                            }

                            TabData.OVERSEA_STAY.route -> {
                                OverSeaContent(modifier = pageModifier)
                            }

                            TabData.LEISURE.route -> {
                                LeisureContent(
                                    modifier = pageModifier,
                                    list = leisureSearchWordData
                                )
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
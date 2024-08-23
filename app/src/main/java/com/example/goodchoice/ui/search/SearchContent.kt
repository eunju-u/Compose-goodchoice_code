package com.example.goodchoice.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.preference.GoodChoicePreference
import com.example.goodchoice.ui.main.MainViewModel
import java.time.LocalDate
import com.example.common.R
import com.example.common.components.TabWidget
import com.example.common.data.TabData
import com.example.common.theme.Theme
import com.example.common.theme.*
import com.example.common.utils.ConvertUtil
import com.example.domain.info.ConnectInfo

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
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { menus.size })

    val leisureSearchWordData = viewModel.leisureSearchWordData
    val leisureSearchAreaData = viewModel.leisureSearchAreaData
    val koreaSearchRankData = viewModel.koreaSearchRankData

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
                divider = { HorizontalDivider(color = Theme.colorScheme.pureGray) }
            ) { state ->
                HorizontalPager(
                    modifier = pageModifier,
                    state = state,
                    verticalAlignment = Alignment.Top,
                    userScrollEnabled = false
                ) { page: Int ->
                    if (homeUiState.value is ConnectInfo.Available) {
                        when (menus[page].route) {
                            TabData.KOREA_STAY.route -> {
                                // 국내숙소
                                KoreaStayContent(
                                    modifier = pageModifier,
                                    rankList = koreaSearchRankData
                                )
                            }

                            TabData.OVERSEA_STAY.route -> {
                                val startDate = pref.overseaStartDate
                                val endDate = pref.overseaEndDate

                                val startDateFormat = ConvertUtil.formatDate(startDate)
                                val endDateFormat = ConvertUtil.formatDate(endDate)
                                val startDayOfWeek =
                                    ConvertUtil.convertDayOfWeek(LocalDate.parse(startDate).dayOfWeek.name)
                                val endDayOfWeek =
                                    ConvertUtil.convertDayOfWeek(LocalDate.parse(endDate).dayOfWeek.name)
                                val date =
                                    "$startDateFormat ${stringResource(id = startDayOfWeek)} " +
                                            "- $endDateFormat ${stringResource(id = endDayOfWeek)}"
                                val stayCount = stringResource(
                                    id = R.string.str_guest_room_count,
                                    pref.overseaStayCount
                                )
                                val adultCount =
                                    stringResource(
                                        id = R.string.str_adult_count,
                                        pref.overseaAdultCount
                                    )
                                val kidCount =
                                    stringResource(
                                        id = R.string.str_kid_count,
                                        pref.overseaKidCount
                                    )
                                // 해외숙소
                                OverSeaContent(
                                    modifier = pageModifier, date = date,
                                    text = "${stayCount}, ${adultCount}, $kidCount"

                                )
                            }

                            TabData.LEISURE.route -> {
                                // 레저*티켓
                                LeisureContent(
                                    modifier = pageModifier,
                                    wordList = leisureSearchWordData,
                                    areaList = leisureSearchAreaData
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
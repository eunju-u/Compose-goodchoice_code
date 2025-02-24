package com.example.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.common.ui_data.TabData
import com.example.data.local.preference.GoodChoicePreference
import com.example.domain.intent.LikeIntent
import java.time.LocalDate
import com.example.ui_common.R
import com.example.ui_common.components.TabWidget
import com.example.ui_theme.*
import com.example.search.ui.state.SearchUiState
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.utils.ConvertUtil

private val menus = listOf(
    TabData.KOREA_STAY, TabData.OVERSEA_STAY, TabData.LEISURE
)

/**
 * 검색 화면
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier, viewModel: SearchViewModel
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val searchUiState by viewModel.searchState.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { menus.size })
    var isShowDialog by remember { mutableStateOf(false) }

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
                    if (searchUiState is SearchUiState.Success) {
                        val koreaRankData = (searchUiState as SearchUiState.Success).koreaRankData
                        val leisureSearchWordData =
                            (searchUiState as SearchUiState.Success).recommendWordData
                        val leisureSearchAreaData =
                            (searchUiState as SearchUiState.Success).areaData

                        when (menus[page].route) {
                            TabData.KOREA_STAY.route -> {
                                // 국내숙소
                                KoreaStayContent(
                                    modifier = pageModifier,
                                    rankList = koreaRankData
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

        if (isShowDialog) {
            AlertDialogWidget(
                onDismiss = {},
                title = stringResource(id = R.string.str_dialog_network_not_connect),
                oneButtonText = stringResource(id = R.string.str_ok),
                onConfirm = {
                    isShowDialog = false
                    viewModel.sendIntent(CommonIntent.Retry("reload"))
                },
            )
        }

        when (searchUiState) {
            is SearchUiState.Loading -> LoadingWidget()
            is SearchUiState.Error -> {
                isShowDialog = true
            }

            else -> {}
        }

    }
}
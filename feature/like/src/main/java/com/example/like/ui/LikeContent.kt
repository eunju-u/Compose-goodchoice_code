package com.example.like.ui

import android.content.Intent
import android.net.Uri
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
import com.example.ui_common.R
import com.example.common.Const
import com.example.common.ui_data.TabData
import com.example.ui_common.components.GoToWidget
import com.example.ui_common.components.TabWidget
import com.example.domain.info.ConnectInfo
import com.example.data.local.preference.GoodChoicePreference
import com.example.like.ui.widget.EmptyDataWidget
import com.example.like.ui.widget.KoreaStayLikeContent
import com.example.ui_theme.*

private val menus = listOf(
    TabData.KOREA, TabData.OVERSEA, TabData.RENTAL, TabData.LEISURE
)

/**
 * 찜 화면
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikeContent(
    modifier: Modifier = Modifier, viewModel: LikeViewModel
) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val koreaLikeData = viewModel.koreaLikeData
    val overseaLikeData = viewModel.overseaLikeData
    val spaceRentalLikeData = viewModel.spaceRentalLikeData
    val leisureLikeData = viewModel.leisureLikeData

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { menus.size })
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()

    val pageModifier = Modifier
        .fillMaxSize()
        .background(color = Theme.colorScheme.pureGray)

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabWidget(
                menus = menus,
                pagerState = pagerState,
                selectedContentColor = Theme.colorScheme.blue,
                unselectedContentColor = Theme.colorScheme.gray,
                divider = {
                    HorizontalDivider(color = Theme.colorScheme.pureGray)
                }
            ) { state ->
                HorizontalPager(
                    modifier = pageModifier,
                    state = state,
                    verticalAlignment = Alignment.Top
                ) { page: Int ->
                    if (!pref.isLogin) {
                        GoToWidget(
                            modifier = Modifier.fillMaxSize(),
                            firstText = stringResource(id = R.string.str_no_see_like_list),
                            secondText = stringResource(id = R.string.str_check_like_list_after_login),
                            thirdText = stringResource(id = R.string.str_login),
                            onClick = {
                                context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("feature://login")
                                })
                            }
                        )
                    } else {
                        if (homeUiState.value is ConnectInfo.Available) {
                            when (menus[page].route) {
                                TabData.KOREA.route -> {
                                    Column(
                                        modifier = pageModifier
                                    ) {
                                        if (koreaLikeData.isEmpty()) {
                                            EmptyDataWidget(
                                                value = stringResource(id = R.string.str_stay),
                                                onClick = {})
                                        } else {
                                            KoreaStayLikeContent(
                                                koreaLikeData,
                                                clickLike = {
                                                    viewModel.checkLikeData(it)
                                                },
                                                onItemClick = { stayItem ->
                                                    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                                        data =
                                                            Uri.parse("feature://stay_detail?${Const.ITEM_ID}=${stayItem.id}&${Const.ITEM_TITLE}=${stayItem.name}")
                                                    })
                                                })
                                        }
                                    }
                                }

                                TabData.OVERSEA.route -> {
                                    Column(
                                        modifier = pageModifier
                                    ) {
                                        if (overseaLikeData.isEmpty()) {
                                            EmptyDataWidget(
                                                value = stringResource(id = R.string.str_stay),
                                                onClick = {})
                                        } else {

                                        }
                                    }
                                }

                                TabData.RENTAL.route -> {
                                    Column(
                                        modifier = pageModifier
                                    ) {
                                        if (spaceRentalLikeData.isEmpty()) {
                                            EmptyDataWidget(
                                                value = stringResource(id = R.string.str_space),
                                                onClick = {})
                                        }
                                    }
                                }

                                TabData.LEISURE.route -> {
                                    Column(
                                        modifier = pageModifier
                                    ) {
                                        if (leisureLikeData.isEmpty()) {
                                            EmptyDataWidget(
                                                value = stringResource(id = R.string.str_ticket),
                                                onClick = {})
                                        }
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}
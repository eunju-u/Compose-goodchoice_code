package com.example.goodchoice.ui.like

import android.content.Intent
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
import com.example.common.R
import com.example.common.data.TabData
import com.example.goodchoice.ConnectInfo
import com.example.common.Const
import com.example.common.components.GoToWidget
import com.example.common.components.TabWidget
import com.example.common.theme.Theme
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.like.widget.EmptyDataWidget
import com.example.goodchoice.ui.like.widget.KoreaStayLikeContent
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.stayDetail.StayDetailActivity

private val menus = listOf(
    TabData.KOREA, TabData.OVERSEA, TabData.RENTAL, TabData.LEISURE
)

/**
 * 찜 화면
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikeContent(
    modifier: Modifier = Modifier, viewModel: MainViewModel
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
                                context.startActivity(Intent(context, LoginActivity::class.java))
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
                                                    context.startActivity(
                                                        Intent(
                                                            context,
                                                            StayDetailActivity::class.java
                                                        ).apply {
                                                            putExtra(Const.ITEM_ID, stayItem.id)
                                                            putExtra(
                                                                Const.ITEM_TITLE,
                                                                stayItem.name
                                                            )
                                                        }
                                                    )
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
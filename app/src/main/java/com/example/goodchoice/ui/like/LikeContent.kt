package com.example.goodchoice.ui.like

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.TabData
import com.example.goodchoice.ui.components.GoToWidget
import com.example.goodchoice.ui.components.TabWidget
import com.example.goodchoice.ui.like.widget.EmptyDataWidget
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp30

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

    val pagerState = rememberPagerState(initialPage = 0)
    val likeUiState = viewModel.likeUiState.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabWidget(
                menus = menus,
                pagerState = pagerState,
                selectedContentColor = Theme.colorScheme.blue,
                unselectedContentColor = Theme.colorScheme.gray
            ) { state ->
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Theme.colorScheme.pureGray),
                    pageCount = menus.size,
                    state = state,
                    verticalAlignment = Alignment.Top
                ) { page: Int ->
                    if (!pref.isLogin()) {
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
                        if (likeUiState.value is ConnectInfo.Available) {
                            when (menus[page].route) {
                                TabData.KOREA.route -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(color = Theme.colorScheme.pureGray)
                                            .padding(start = dp30, end = dp30),
                                    ) {
                                        val stay = stringResource(id = R.string.str_stay)
                                        if (koreaLikeData.isEmpty()) {
                                            EmptyDataWidget(value = stay, onClick = {})
                                        }
                                    }
                                }
                                TabData.OVERSEA.route -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(color = Theme.colorScheme.pureGray)
                                            .padding(start = dp30, end = dp30),
                                    ) {
                                        val stay = stringResource(id = R.string.str_stay)
                                        if (overseaLikeData.isEmpty()) {
                                            EmptyDataWidget(value = stay, onClick = {})
                                        }
                                    }
                                }
                                TabData.RENTAL.route -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(color = Theme.colorScheme.pureGray)
                                            .padding(start = dp30, end = dp30),
                                    ) {
                                        val space = stringResource(id = R.string.str_space)
                                        if (spaceRentalLikeData.isEmpty()) {
                                            EmptyDataWidget(value = space, onClick = {})
                                        }
                                    }
                                }
                                TabData.LEISURE.route -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(color = Theme.colorScheme.pureGray)
                                            .padding(start = dp30, end = dp30),
                                    ) {
                                        val ticket = stringResource(id = R.string.str_ticket)
                                        if (leisureLikeData.isEmpty()) {
                                            EmptyDataWidget(value = ticket, onClick = {})
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
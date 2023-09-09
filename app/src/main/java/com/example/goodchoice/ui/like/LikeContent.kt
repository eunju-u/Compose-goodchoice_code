package com.example.goodchoice.ui.like

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.goodchoice.ui.TabData
import com.example.goodchoice.ui.components.TabWidget
import com.example.goodchoice.ui.like.widget.LikeLoginWidget
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.Theme

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
    val koreaLikeData = viewModel.koreaLikeData
    val overseaLikeData = viewModel.overseaLikeData
    val spaceRentalLikeData = viewModel.spaceRentalLikeData
    val leisureLikeData = viewModel.leisureLikeData

    val pagerState = rememberPagerState(initialPage = 0)

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
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = Theme.colorScheme.white),
                    pageCount = menus.size,
                    state = state,
                    verticalAlignment = Alignment.Top
                ) { page: Int ->
                    //TODO eunju : 로그인 여부
                    if (true) {
                        LikeLoginWidget(Modifier.fillMaxSize())
                    } else {
                        when (menus[page].route) {
                            TabData.KOREA.route -> {
                            }
                            TabData.OVERSEA.route -> {
                            }
                            TabData.RENTAL.route -> {
                            }
                            TabData.LEISURE.route -> {
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
package com.example.goodchoice.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.TabData
import com.example.goodchoice.ui.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabWidget(
    modifier: Modifier = Modifier,
    menus: List<TabData>,
    pagerState: PagerState,
    tabBackgroundColor: Color = Theme.colorScheme.white,
    selectedContentColor: Color = Theme.colorScheme.darkGray,
    unselectedContentColor: Color = Theme.colorScheme.gray,
    selectedTextStyle: TextStyle = MaterialTheme.typography.labelLarge,
    unselectedTextStyle: TextStyle = MaterialTheme.typography.labelLarge,
    height: Dp = 1.5.dp,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = { tabPositions ->
        SecondaryIndicator(
            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
            height = height,
            color = selectedContentColor
        )
    },
    divider: @Composable () -> Unit = {},
    content: @Composable ((state: PagerState) -> Unit) = {}
) {
    val scope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier
            .background(color = tabBackgroundColor)
            .then(modifier),
        selectedTabIndex = pagerState.currentPage,
        containerColor = tabBackgroundColor,
        indicator = { tabPositions -> indicator(tabPositions) },
        divider = divider
    ) {
        menus.forEachIndexed { index, tabData ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                text = {
                    TextWidget(
                        text = tabData.name,
                        style = if (pagerState.currentPage == index) selectedTextStyle else unselectedTextStyle,
                        color = if (pagerState.currentPage == index) selectedContentColor else unselectedContentColor,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            )
        }
    }
    content(pagerState)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabWidgetPreview() {
    TabWidget(
        pagerState = rememberPagerState(pageCount = { 0 }),
        menus = listOf(
            TabData.KOREA, TabData.OVERSEA
        )
    ) {}
}
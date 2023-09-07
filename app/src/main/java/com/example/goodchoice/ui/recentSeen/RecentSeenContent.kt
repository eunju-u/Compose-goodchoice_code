package com.example.goodchoice.ui.recentSeen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.*

/**
 * 최근 본 상품 화면
 */
@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun RecentSeenContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val homeData = viewModel.homeData.value
    val stayList =  homeData.recentStay.value.stayList?: mutableStateListOf()

    Column {
        TopAppBarWidget(
            title = stringResource(id = R.string.str_recent_seen_item),
            onFinish = { (context as RecentSeenActivity).finish() }) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        viewModel.isRemoveRecentList = true
                        stayList.clear()
                    }
                    .padding(start = dp5, end = dp5)
                    .wrapContentHeight(Alignment.CenterVertically),
                text = stringResource(id = R.string.str_all_remove),
                style = MaterialTheme.typography.labelSmall,
                color = Theme.colorScheme.darkGray,
                textAlign = TextAlign.Center
            )
        }

        when (homeUiState.value) {
            is ConnectInfo.Available -> {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .background(Theme.colorScheme.white)
                ) {
                    if (stayList.isNotEmpty()) {
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = dp20, top = dp20, bottom = dp15),
                                text = homeData.recentStay.value.title ?: "",
                                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                        itemsIndexed(items = stayList) { index, item ->
                            RecentSeenItemWidget(item)
                        }
                    } else {
                        item {
                            Text(
                                text = stringResource(id = R.string.str_empty_recent_seen_item_title),
                                color = Theme.colorScheme.gray,
                                style = MaterialTheme.typography.labelSmall
                            )

                            Spacer(modifier = Modifier.height(dp10))
                            Text(
                                text = stringResource(id = R.string.str_empty_recent_seen_item_sub),
                                color = Theme.colorScheme.pureGray,
                                style = MaterialTheme.typography.labelSmall
                            )
                            Spacer(modifier = Modifier.height(dp10))

                            ButtonWidget(content = {
                                Text(
                                    text = stringResource(id = R.string.str_see_item),
                                    color = Theme.colorScheme.panoramaBlue,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }, onItemClick = {
                            })
                        }
                    }
                }
            }
            is ConnectInfo.Loading -> {

            }
            is ConnectInfo.Error -> {

            }
            else -> {}
        }
    }
}
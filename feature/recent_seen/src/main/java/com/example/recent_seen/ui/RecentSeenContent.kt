package com.example.recent_seen.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui_common.R
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.TopAppBarWidget
import com.example.ui_theme.*

/**
 * 최근 본 상품 화면
 */
@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun RecentSeenContent(viewModel: RecentSeenViewModel) {
    val context = LocalContext.current
    val stayList = viewModel.recentDbList.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    val isScrolled by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }

    Column {
        TopAppBarWidget(
            onFinish = { (context as RecentSeenActivity).finish() },
            title = if (isScrolled) stringResource(id = R.string.str_recent_seen_item) else "",
            rightContent = {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            var str = R.string.str_recent_seen_all_remove_not_list
                            if (stayList.value.isNotEmpty()) {
                                str = R.string.str_recent_seen_all_remove
                                //최근 본 상품 DB 내용 삭제
                                viewModel.deleteRecentDb()
                            }
                            Toast
                                .makeText(
                                    context,
                                    str,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .padding(start = dp5, end = dp5)
                        .wrapContentHeight(Alignment.CenterVertically),
                    text = stringResource(id = R.string.str_all_remove),
                    style = MaterialTheme.typography.labelSmall,
                    color = Theme.colorScheme.darkGray,
                    textAlign = TextAlign.Center
                )
            }
        )

        val modifier = Modifier
            .fillMaxSize()
            .background(Theme.colorScheme.white)
        if (stayList.value.isNotEmpty()) {
            LazyColumn(
                modifier = modifier,
                state = scrollState
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = dp20, top = dp20, bottom = dp15),
                        text = context.getString(R.string.str_recent_seen_item),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
                itemsIndexed(items = stayList.value) { index, item ->
                    RecentSeenItemWidget(item)
                }
            }
        } else {
            Column(
                modifier = modifier.padding(dp30),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.str_empty_recent_seen_item_title),
                    color = Theme.colorScheme.darkGray,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(dp10))
                Text(
                    text = stringResource(id = R.string.str_empty_recent_seen_item_sub),
                    color = Theme.colorScheme.gray,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(dp10))
                CardWidget(content = {
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
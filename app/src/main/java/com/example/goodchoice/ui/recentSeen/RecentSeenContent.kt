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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.Const
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.R
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.*

/**
 * 최근 본 상품 화면
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecentSeenContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    val homeData = viewModel.homeData.value
    Log.d("eunju", "homeData $homeData")

    val recentStayList = homeData.recentStayList ?: emptyList()
    val filterRecentList = recentStayList.filter { stayData -> stayData.type == Const.RECENT_HOTEL }
    Column {
        TopAppBarWidget(
            title = stringResource(id = R.string.str_recent_seen_item),
            onFinish = { (context as RecentSeenActivity).finish() }) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {}
                    .padding(start = dp5, end = dp5)
                    .wrapContentHeight(Alignment.CenterVertically),
                text = stringResource(id = R.string.str_all_remove),
                style = MaterialTheme.typography.labelSmall,
                color = Theme.colorScheme.darkGray,
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Theme.colorScheme.white)
        ) {
            if (filterRecentList.isNotEmpty()) {
                val stayData = filterRecentList[0]
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = dp20, top = dp20, bottom = dp15),
                        text = stayData.title ?: "",
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
                stayData.stayList?.let { stayList ->
                    itemsIndexed(items = stayList) { index, item ->
                        RecentSeenItemWidget(item)
                    }
                }
            }
        }
    }
}
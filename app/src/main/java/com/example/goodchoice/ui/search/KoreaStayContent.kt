package com.example.goodchoice.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.goodchoice.R
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.components.RowTwoWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun KoreaStayContent(
    modifier: Modifier = Modifier,
    date: String = "",
    personCount: Int = 2,
    rankList: List<FilterItem> = emptyList(),
    onLeftItemClick: () -> Unit = {},
    onRightItemClick: () -> Unit = {}
) {
    val innerPadding = PaddingValues(horizontal = dp15, vertical = dp15)

    LazyColumn(
        modifier = modifier
    ) {
        item {
            LeftImageButtonWidget(
                modifier = Modifier.fillMaxWidth(),
                outerPadding = PaddingValues(top = dp30, start = dp20, end = dp20),
                innerPadding = innerPadding,
                isCenterHorizontalArrangement = false,
                title = stringResource(id = R.string.str_search_korea_area),
                containerColor = Theme.colorScheme.pureGray,
                contentColor = Theme.colorScheme.gray,
                shape = dp10,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_nav_search),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                        contentDescription = null
                    )
                },
                onItemClick = { }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dp15, start = dp20, end = dp20),
                horizontalArrangement = Arrangement.spacedBy(dp10)
            ) {
                LeftImageButtonWidget(
                    modifier = Modifier.weight(0.8f),
                    title = date,
                    containerColor = Theme.colorScheme.pureGray,
                    contentColor = Theme.colorScheme.darkGray,
                    shape = dp10,
                    style = MaterialTheme.typography.labelLarge,
                    isCenterHorizontalArrangement = false,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    innerPadding = innerPadding,
                    content = {
                        Image(
                            modifier = Modifier.size(dp20),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                            contentDescription = null
                        )
                    },
                    onItemClick = onLeftItemClick
                )
                LeftImageButtonWidget(
                    title = stringResource(
                        id = R.string.str_person_count,
                        personCount
                    ),
                    containerColor = Theme.colorScheme.pureGray,
                    contentColor = Theme.colorScheme.darkGray,
                    shape = dp10,
                    style = MaterialTheme.typography.labelLarge,
                    isCenterHorizontalArrangement = false,
                    innerPadding = innerPadding,
                    content = {
                        Image(
                            modifier = Modifier.size(dp20),
                            painter = painterResource(id = R.drawable.ic_nav_my_page),
                            colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                            contentDescription = null
                        )
                    },
                    onItemClick = onRightItemClick
                )
            }

            LeftImageButtonWidget(
                title = stringResource(id = R.string.str_search_my_arround),
                outerPadding = PaddingValues(top = dp25, start = dp20, end = dp20, bottom = dp20),
                contentColor = Theme.colorScheme.blue,
                borderColor = Theme.colorScheme.blue,
                shape = dp20,
                style = MaterialTheme.typography.labelMedium,
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_send_message),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.blue),
                        contentDescription = null
                    )
                }
            )

            Text(
                modifier = Modifier.padding(top = dp15, start = dp20, end = dp20, bottom = dp15),
                text = stringResource(id = R.string.str_search_rank),
                style = MaterialTheme.typography.labelLarge,
                color = Theme.colorScheme.darkGray
            )
        }

        itemsIndexed(items = rankList) { idx, item ->
            RowTwoWidget(
                modifier = Modifier.fillMaxWidth(),
                innerPadding = PaddingValues(top = dp10, bottom = dp10, start = dp20, end = dp20),
                leftModifier = Modifier.width(dp25),
                leftText = (idx + 1).toString(),
                rightText = item.filterTitle,
                leftStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                rightStyle = MaterialTheme.typography.labelLarge,
                endPadding = dp20,
                onItemClick = {}
            )
        }
    }
}
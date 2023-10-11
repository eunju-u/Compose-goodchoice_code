package com.example.goodchoice.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun KoreaStayContent(
    modifier: Modifier = Modifier,
    date: String = "",
    personCount: Int = 2,
    onLeftItemClick: () -> Unit = {},
    onRightItemClick: () -> Unit = {}
) {
    val innerPadding = PaddingValues(horizontal = dp20, vertical = dp15)

    Column(
        modifier = modifier
    ) {
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                innerPadding = innerPadding,
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
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
                innerPadding = innerPadding,
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_nav_my_page),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                        contentDescription = null
                    )
                },
                onItemClick = onRightItemClick
            )
        }
    }
}
package com.example.goodchoice.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.goodchoice.api.data.FilterItem
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.components.TagWidget
import com.example.goodchoice.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LeisureContent(
    modifier: Modifier = Modifier,
    list: List<FilterItem> = emptyList(),
    onItemClick: () -> Unit = {}
) {
    val innerPadding = PaddingValues(horizontal = dp20, vertical = dp15)

    Column(
        modifier = modifier
    ) {
        LeftImageButtonWidget(
            modifier = Modifier.fillMaxWidth(),
            outerPadding = PaddingValues(top = dp30, bottom = dp30, start = dp20, end = dp20),
            innerPadding = innerPadding,
            isCenterHorizontalArrangement = false,
            title = stringResource(id = R.string.str_search_reisure_area),
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

        Text(
            modifier = Modifier.padding(start = dp20, end = dp20, bottom = dp15),
            text = stringResource(id = R.string.str_search_recommend),
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
        )

        FlowRow(modifier = Modifier.padding(start = dp15, end = dp15)) {
            list.forEach {
                TagWidget(
                    outerPadding = PaddingValues(dp7),
                    containerColor = Theme.colorScheme.white,
                    borderColor = Theme.colorScheme.pureGray,
                    onItemClick = { },
                    title = it.filterTitle ?: "", style = MaterialTheme.typography.labelMedium,
                    contentColor = Theme.colorScheme.darkGray,
                )
            }
        }
    }
}
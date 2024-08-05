package com.example.goodchoice.ui.filter.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.components.TagWidget
import com.example.common.theme.Theme
import com.example.common.theme.*
import com.example.goodchoice.data.dto.FilterItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterItemWidget(
    subTitle: String = "",
    list: List<FilterItem> = listOf(),
    selectList: List<FilterItem> = listOf(),
    onItemClick: (item: FilterItem) -> Unit = {}
) {
    if (subTitle.isNotEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp10, bottom = dp10),
            text = subTitle,
            style = MaterialTheme.typography.labelMedium,
            color = Theme.colorScheme.gray
        )
    }

    FlowRow {
        list.forEach {
            TagWidget(
                outerPadding = PaddingValues(dp5),
                containerColor = if (selectList.contains(it)) Theme.colorScheme.pureBlue else Theme.colorScheme.white,
                borderColor = if (selectList.contains(it)) Theme.colorScheme.blue else Theme.colorScheme.pureGray,
                onItemClick = { onItemClick(it) },
                title = it.filterTitle ?: "", style = MaterialTheme.typography.labelMedium,
                contentColor = if (selectList.contains(it)) Theme.colorScheme.blue else Theme.colorScheme.darkGray,
            )
        }
    }
}
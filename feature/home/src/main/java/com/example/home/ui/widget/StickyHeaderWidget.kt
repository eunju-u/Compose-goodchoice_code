package com.example.home.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.home.domain.model.CategoryItem
import com.example.ui_theme.*
import com.example.ui_common.R
import androidx.compose.ui.graphics.ColorFilter
import com.example.ui_common.components.CategoryItemWidget

/**
 * 홈 스크롤시 상단에 노출되는 뷰
 */
@Composable
fun StickyHeaderWidget(
    modifier: Modifier = Modifier,
    categoryItem: List<CategoryItem> = emptyList(),
    onClickMore: () -> Unit
) {
    val backgroundColor = Theme.colorScheme.white
    Surface(
        modifier = modifier,
        color = backgroundColor,
        contentColor = contentColorFor(backgroundColor),
        shadowElevation = dp20
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
                .padding(top = dp10, bottom = dp10, start = dp30, end = dp30),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //카테고리 노출
            if (categoryItem.size > 4) {
                for (i in 0..3) {
                    val item = categoryItem[i]
                    CategoryItemWidget(
                        painter = painterResource(id = item.icon ?: R.drawable.bg_white),
                        name = item.name ?: "",
                        height = CategoryItemHeight
                    )
                }
                VerticalDivider(
                    modifier = Modifier
                        .width(dp1)
                        .height(dp30), color = Theme.colorScheme.pureGray
                )
                CategoryItemWidget(
                    painter = painterResource(id = R.drawable.ic_more),
                    name = stringResource(id = R.string.str_more),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                    height = CategoryItemHeight,
                    onItemClick = { onClickMore() }
                )
            } else {
                for (i in categoryItem.indices) {
                    val item = categoryItem[i]
                    CategoryItemWidget(
                        painter = painterResource(id = item.icon ?: R.drawable.bg_white),
                        name = item.name ?: "",
                        height = CategoryItemHeight
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewStickyHeaderWidget() {
    StickyHeaderWidget(
        categoryItem = listOf(
            CategoryItem(
                "",
                "캠핑*글램핑",
                R.drawable.ic_airplane
            )
        )
    ) {}
}
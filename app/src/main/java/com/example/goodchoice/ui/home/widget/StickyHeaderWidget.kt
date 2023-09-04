package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.data.CategoryItem
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*

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
        elevation = dp20
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
                    CategoryItemWidget(item = categoryItem[i])
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(dp20), color = Theme.colorScheme.gray
                )
                IconButton(modifier = Modifier
                    .size(dp30), onClick = { onClickMore() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = stringResource(id = R.string.str_more)
                    )
                }
            } else {
                for (i in categoryItem.indices) {
                    CategoryItemWidget(item = categoryItem[i])
                }
            }
        }
    }
}

@Preview
@Composable
fun previewStickyHeaderWidget() {
    StickyHeaderWidget(
        categoryItem = listOf(CategoryItem(5, "캠핑*글램핑", R.drawable.ic_airplane))
    ) {}
}
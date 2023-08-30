package com.example.goodchoice.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.goodchoice.data.CategoryItem
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R

@Composable
fun StickyHeaderWidget(
    modifier: Modifier = Modifier,
    categoryItem: List<CategoryItem>,
    onClickMore: () -> Unit
) {
    Row(
        modifier = modifier,
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
                    .height(15.dp), color = Theme.colorScheme.gray
            )
            IconButton(modifier = Modifier
                .size(30.dp), onClick = { onClickMore() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "더보기"
                )
            }

        } else {
            for (i in categoryItem.indices) {
                CategoryItemWidget(item = categoryItem[i])
            }
        }
    }
}
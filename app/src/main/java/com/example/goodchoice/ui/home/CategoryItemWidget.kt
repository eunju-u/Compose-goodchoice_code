package com.example.goodchoice.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.data.CategoryItem
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.theme.CategoryItemHeight
import com.example.goodchoice.ui.theme.dp35

@Composable
fun CategoryItemWidget(item: CategoryItem = CategoryItem()) {
    Column(
        modifier = Modifier.height(CategoryItemHeight),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(dp35)
                .padding(bottom = 5.dp),
            painter = painterResource(id = item.icon),
            contentDescription = item.name,
            alpha = LocalContentAlpha.current
        )
        TextWidget(
            text = item.name, style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Preview
@Composable
fun previewCategoryItemWidget() {
    CategoryItemWidget(CategoryItem(7, "공간대여", R.drawable.ic_airplane))
}
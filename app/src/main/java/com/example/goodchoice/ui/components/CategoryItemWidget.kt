package com.example.goodchoice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.api.data.CategoryItem
import com.example.goodchoice.ui.theme.CategoryItemHeight
import com.example.goodchoice.ui.theme.dp2
import com.example.goodchoice.ui.theme.dp35

@Composable
fun CategoryItemWidget(item: CategoryItem = CategoryItem()) {
    Column(
        modifier = Modifier
            .height(CategoryItemHeight)
            .clickable { },
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
            modifier = Modifier.padding(bottom = dp2),
            text = item.name, style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun previewCategoryItemWidget() {
    CategoryItemWidget(CategoryItem(7, "공간대여", R.drawable.ic_airplane))
}
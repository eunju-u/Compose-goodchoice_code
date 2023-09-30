package com.example.goodchoice.ui.home.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.api.data.CategoryItem
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.CategoryItemWidget
import com.example.goodchoice.ui.theme.CategoryItemHeight

@SuppressLint("RememberReturnType")
@Composable
fun FullHeaderWidget(
    categoryItem: List<CategoryItem> = emptyList(),
    onClickClose: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val fullHeight = configuration.screenHeightDp
    val ratio = 0.6f
    val row = 4

    BoxWithConstraints(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize()) {
            val color = Theme.colorScheme.darkGray
            Canvas(
                Modifier
                    .fillMaxSize()
                    .pointerInput(true) {}
                    .semantics(mergeDescendants = true) {
                        onClick { false }
                    }

            ) {
                drawRect(
                    color = color, alpha = 0.3f
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = (fullHeight * ratio).dp)
                        .padding(10.dp),
                    columns = GridCells.Fixed(count = row),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    items(items = categoryItem) { item ->
                        CategoryItemWidget(
                            icon = item.icon,
                            name = item.name,
                            height = CategoryItemHeight
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            RoundCloseWidget(modifier = Modifier.clickable { onClickClose() })
        }
    }
}

@Preview
@Composable
fun previewFullHeaderWidget() {
    FullHeaderWidget(
        listOf(
            CategoryItem(0, "프리미엄블랙", R.drawable.ic_airplane),
            CategoryItem(1, "모텔", R.drawable.ic_airplane)
        )
    ) {}
}
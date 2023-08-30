package com.example.goodchoice.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.goodchoice.data.CategoryItem
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.R

@SuppressLint("RememberReturnType")
@Composable
fun FullHeaderWidget(
    categoryItem: List<CategoryItem>,

    onClickClose: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val fullHeight = configuration.screenHeightDp
    val ratio = 0.8f
    val row = 4

    BoxWithConstraints(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize()) {
            val color = Theme.colorScheme.darkGray
            Canvas(
                Modifier
                    .fillMaxSize()
                    .pointerInput(true) { detectTapGestures { } }
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
                        .padding(20.dp),
                    columns = GridCells.Fixed(count = row),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    itemsIndexed(items = categoryItem) { index, item ->
                        CategoryItemWidget(item)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onClickClose() },
                painter = painterResource(id = R.drawable.ic_circle_close),
                contentDescription = "닫기"
            )
        }
    }
}
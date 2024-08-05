package com.example.goodchoice.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.common.R
import com.example.common.components.*
import com.example.common.theme.Theme
import com.example.common.theme.*
import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.data.dto.RecommendAreaData

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LeisureContent(
    modifier: Modifier = Modifier,
    wordList: List<FilterItem> = emptyList(),
    areaList: List<RecommendAreaData> = emptyList(),
    onItemClick: () -> Unit = {}
) {
    val innerPadding = PaddingValues(horizontal = dp20, vertical = dp15)

    LazyColumn(
        modifier = modifier
    ) {
        item {
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
                text = stringResource(id = R.string.str_search_recommend_word),
                color = Theme.colorScheme.darkGray,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )

            FlowRow(modifier = Modifier.padding(start = dp15, end = dp15)) {
                wordList.forEach {
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

            Text(
                modifier = Modifier.padding(start = dp20, end = dp20, top = dp30),
                text = stringResource(id = R.string.str_search_recommend_area),
                color = Theme.colorScheme.darkGray,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )



            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dp15, bottom = dp15),
                horizontalArrangement = Arrangement.spacedBy(dp10)
            ) {
                itemsIndexed(items = areaList) { index, item ->
                    val painter =
                        if (item.image?.isNotEmpty() == true)
                            rememberAsyncImagePainter(
                                model = item.image,
                                painterResource(id = R.drawable.bg_white)
                            )
                        else painterResource(id = R.drawable.bg_white)

                    if (index == 0) Spacer(Modifier.width(dp20))
                    CategoryItemWidget(
                        width = dp60,
                        imageSize = dp55,
                        imageClip = dp30,
                        bottomPadding = dp15,
                        textStyle = MaterialTheme.typography.labelMedium,
                        textColor = Theme.colorScheme.gray,
                        painter = painter,
                        name = item.title ?: "",
                        contentScale = ContentScale.Crop
                    )
                    if (index == areaList.lastIndex) Spacer(Modifier.width(dp20))
                }
            }
        }
    }
}
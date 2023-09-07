package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.api.data.BannerData
import com.example.goodchoice.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerWidget(modifier: Modifier = Modifier, bannerList: List<BannerData> = emptyList()) {
    val pagerState = rememberPagerState(initialPage = 0)
    val currentPage = pagerState.currentPage
    val listSize = bannerList.size
    val userScrollEnabled = listSize > 1

    Box(
        modifier = modifier
    ) {

        HorizontalPager(
            state = pagerState,
            pageCount = Int.MAX_VALUE,
            userScrollEnabled = userScrollEnabled
        ) { page ->
            val index = page % listSize
            val item = bannerList[index]
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = item.image),
//                painter = rememberAsyncImagePainter(
//                    ImageRequest.Builder(LocalContext.current)
//                        .data(data = item.image)
//                        .apply(block = fun ImageRequest.Builder.() {
//                            size(Size.ORIGINAL)
//                        }).build()
//                )
                contentDescription = "배너",
                contentScale = ContentScale.FillWidth
            )
        }

        if (listSize > 1) {
            val textString = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Theme.colorScheme.white,
                    )
                ) {
                    append(((currentPage) % 3 + 1).toString())
                }
                append("/${listSize}")
            }

            /** page 표시 뷰**/
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = dp15, bottom = dp15)
                    .width(dp55)
                    .height(dp25)
                    .background(
                        color = Theme.colorScheme.darkGray,
                        shape = RoundedCornerShape(dp20)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = textString, textAlign = TextAlign.Center,
                    color = Theme.colorScheme.pureGray,
                    style = MaterialTheme.typography.labelSmall
                )
                if (listSize > 25) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_add),
                        contentDescription = "더보기",
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewBannerWidget() {
    BannerWidget(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), bannerList = listOf(
            BannerData(R.drawable.bg_purple),
            BannerData(R.drawable.bg_yellow),
            BannerData(R.drawable.bg_teal)
        )
    )
}
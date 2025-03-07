package com.example.home.ui.widget

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.rememberAsyncImagePainter
import com.example.common.Const
import com.example.ui_common.R
import com.example.ui_theme.*
import com.example.home.domain.model.BannerData

@Composable
fun BannerWidget(modifier: Modifier = Modifier, bannerList: List<BannerData> = emptyList()) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { bannerList.size })
    val currentPage = pagerState.currentPage
    val listSize = bannerList.size
    val userScrollEnabled = listSize > 1

    Box(
        modifier = modifier
    ) {

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = userScrollEnabled
        ) { page ->
            val index = page % listSize
            val item = bannerList[index]
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data =
                                Uri.parse("feature://webview?${Const.WEBVIEW_TITLE}=${item.title}&${Const.WEBVIEW_URL}=${item.url}")
                        })
                    },
                painter =
                if (item.image?.isNotEmpty() == true)
                    rememberAsyncImagePainter(
                        model = item.image,
                        painterResource(id = R.drawable.bg_white)
                    )
                else painterResource(id = R.drawable.bg_white),
                contentDescription = "배너",
            )
        }

        if (listSize > 1) {
            val textString = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Theme.colorScheme.white,
                    )
                ) {
                    append(((currentPage) % listSize + 1).toString())
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
                        contentDescription = stringResource(id = R.string.str_more),
                    )
                }
            }
        }
    }
}

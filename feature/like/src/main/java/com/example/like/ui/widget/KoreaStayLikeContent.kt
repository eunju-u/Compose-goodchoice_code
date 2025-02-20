package com.example.like.ui.widget

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.common.CalendarType
import com.example.common.Const
import com.example.data.local.preference.GoodChoicePreference
import com.example.domain.model.StayItem
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.KoreaDateWidget
import com.example.ui_theme.*

@Composable
fun KoreaStayLikeContent(
    koreaLikeData: List<StayItem>,
    clickLike: (stayId: String) -> Unit = {},
    onItemClick: (stayItem: StayItem) -> Unit = {},
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val pref = GoodChoicePreference(context)

    var itemWidth by remember { mutableStateOf(dp100) }
    var itemHeight by remember { mutableStateOf(dp100) }
    val modifier = Modifier.fillMaxWidth()
    var itemWidthModifier by remember { mutableStateOf(modifier) }

    //해상도에 따라 호텔 item image 크기 조절
    LaunchedEffect(key1 = screenWidth) {
        itemWidth = screenWidth / 3
        itemHeight = screenWidth / 2.6f

        //해상도에 따라 호텔 item view 가로 조정
        itemWidthModifier = if (screenWidth > dp550) {
            Modifier.width(screenWidth / 1.5f)
        } else {
            modifier
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, content = {
            item {
                CardWidget(
                    containerColor = Theme.colorScheme.white,
                    content = {
                        KoreaDateWidget(
                            koreaPersonCount = pref.koreaPersonCount,
                            startDate = pref.koreaStartDate,
                            endDate = pref.koreaEndDate,
                            onLeftItemClick = {
                                context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                    data =
                                        Uri.parse("feature://calendar?${Const.TYPE}=${CalendarType.CALENDAR}")
                                })
                            },
                            onRightItemClick = {
                                context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                    data =
                                        Uri.parse("feature://calendar?${Const.TYPE}=${CalendarType.PERSON}")
                                })
                            }
                        )
                    })
            }

            items(koreaLikeData, key = { it.id ?: "" }) { item ->
                KoreaStayItemWidget(
                    item,
                    itemWidthModifier,
                    itemWidth,
                    itemHeight,
                    { clickLike(item.id ?: "") },
                    { onItemClick(item) })
            }
        })
}
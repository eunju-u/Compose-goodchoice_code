package com.example.goodchoice.ui.like.widget

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val pref = GoodChoicePreference(context)

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, content = {
            item {
                CardWidget(
                    containerColor = Theme.colorScheme.white,
                    content = {  KoreaDateWidget(
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
                    ) })
            }
            item {
                koreaLikeData.forEach { item ->
                    KoreaStayItemWidget(
                        item,
                        { clickLike(item.id ?: "") },
                        { onItemClick(item) })
                }
            }
        })
}
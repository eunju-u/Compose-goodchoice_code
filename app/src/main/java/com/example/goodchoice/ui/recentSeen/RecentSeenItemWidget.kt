package com.example.goodchoice.ui.recentSeen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.R
import com.example.goodchoice.data.StayItem
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.theme.*

/**
 * 최근 본 상품 상세 페이지 각 아이템
 */
@Composable
fun RecentSeenItemWidget(stayItem: StayItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}
            .padding(start = dp25, end = dp25, top = dp10, bottom = dp10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dp15)
    ) {
        val painter =
            if (stayItem.imageList?.isNotEmpty() == true)
                rememberAsyncImagePainter(
                    model = stayItem.imageList[0],
                    painterResource(id = R.drawable.bg_yellow)
                )
            else painterResource(id = R.drawable.bg_yellow)

        RoundImageWidget(modifier = Modifier.size(dp95), painter = painter, roundShape = dp15)
        Column(verticalArrangement = Arrangement.Center) {
            stayItem.label?.let {
                Text(
                    text = it,
                    color = Theme.colorScheme.panoramaBlue,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(dp8))
            }
            stayItem.name?.let {
                Text(
                    text = it,
                    color = Theme.colorScheme.darkGray,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(dp8))
            }
            stayItem.location?.let {
                Text(
                    text = it,
                    color = Theme.colorScheme.gray,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(dp8))
            }
        }
    }
}
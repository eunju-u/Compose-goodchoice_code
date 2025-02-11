package com.example.ui.recentSeen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.common.Const
import com.example.ui.R
import com.example.ui.components.RoundImageWidget
import com.example.domain.model.StayItem
import com.example.ui.stayDetail.StayDetailActivity
import com.example.ui_theme.*

/**
 * 최근 본 상품 상세 페이지 각 아이템
 */
@Composable
fun RecentSeenItemWidget(stayItem: StayItem) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(context, StayDetailActivity::class.java).apply {
                        putExtra(Const.ITEM_ID, stayItem.id)
                        putExtra(Const.ITEM_TITLE, stayItem.name)
                    }
                )
            }
            .padding(start = dp25, end = dp25, top = dp10, bottom = dp10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dp15)
    ) {
        val painter =
            if (stayItem.mainImage?.isNotEmpty() == true)
                rememberAsyncImagePainter(
                    model = stayItem.mainImage,
                    painterResource(id = R.drawable.bg_white)
                )
            else painterResource(id = R.drawable.bg_white)

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
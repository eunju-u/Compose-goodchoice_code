package com.example.goodchoice.ui.home.widget

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.common.Const
import com.example.ui_theme.*
import com.example.ui_common.R
import com.example.ui_common.components.RoundImageWidget
import com.example.domain.model.StayItem
import com.example.ui.stayDetail.StayDetailActivity
import com.example.ui_common.components.TextWidget

/**
 * 홈 > 최근 본 상품
 */
@Composable
fun RecentSeenWidget(stayItem: StayItem) {
    val context = LocalContext.current

    val painter = if (stayItem.mainImage?.isNotEmpty() == true) rememberAsyncImagePainter(
        model = stayItem.mainImage, painterResource(id = R.drawable.bg_white)
    )
    else painterResource(id = R.drawable.bg_white)

    Column(
        modifier = Modifier
            .width(dp120)
            .clip(RoundedCornerShape(dp10))
            .clickable {
                context.startActivity(
                    Intent(context, StayDetailActivity::class.java).apply {
                        putExtra(Const.ITEM_ID, stayItem.id)
                        putExtra(Const.ITEM_TITLE, stayItem.name)
                    }
                )
            }
            .padding(bottom = dp20),
        verticalArrangement = Arrangement.Center,
    ) {
        RoundImageWidget(
            imageModifier = Modifier.height(dp120),
            roundShape = dp10,
            painter = painter,
            content = {
                if (!stayItem.label.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(topEnd = dp5))
                            .alpha(0.65f)
                            .background(color = Theme.colorScheme.darkGray)
                            .padding(start = dp8, end = dp8, top = dp5, bottom = dp5)
                    ) {
                        Text(
                            text = stayItem.label ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Transparent
                        )
                    }
                    Text(
                        modifier = Modifier.padding(
                            start = dp8,
                            end = dp8,
                            top = dp5,
                            bottom = dp5
                        ),
                        text = stayItem.label ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Theme.colorScheme.white
                    )
                }
            }
        )

        TextWidget(
            modifier = Modifier.padding(top = dp8),
            text = stayItem.name ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}
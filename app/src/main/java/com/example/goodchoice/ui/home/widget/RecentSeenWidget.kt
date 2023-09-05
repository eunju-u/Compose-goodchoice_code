package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.R
import com.example.goodchoice.data.StayItem
import com.example.goodchoice.ui.components.RoundImageWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.theme.*

/**
 * 홈 > 최근 본 상품
 */
@Composable
fun RecentSeenWidget(stayItem: StayItem) {
    val painter = if (stayItem.imageList?.isNotEmpty() == true) rememberAsyncImagePainter(
        model = stayItem.imageList[0], painterResource(id = R.drawable.bg_yellow)
    )
    else painterResource(id = R.drawable.bg_yellow)

    Column(
        modifier = Modifier.width(dp120),
        verticalArrangement = Arrangement.Center,
    ) {
        RoundImageWidget(
            modifier = Modifier.height(dp120),
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
                            text = stayItem.label,
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
                        text = stayItem.label,
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
            style = MaterialTheme.typography.labelMedium
        )
    }
}
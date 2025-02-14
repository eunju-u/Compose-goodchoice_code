package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_common.components.LeftImageButtonWidget
import com.example.ui_common.R
import com.example.ui_theme.*

/**
 * 홈 > 이벤트 배너
 */
@Composable
fun EventItemWidget(
    modifier: Modifier = Modifier,
    title: String = ""
) {
    LeftImageButtonWidget(
        modifier = modifier,
        onItemClick = {},
        title = title,
        containerColor = Theme.colorScheme.pureGray,
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
        content = {
            Image(
                modifier = Modifier.size(dp15),
                painter = painterResource(id = R.drawable.ic_brightness),
                colorFilter = ColorFilter.tint(Theme.colorScheme.yellow),
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
fun PreviewEventItemWidget() {
    EventItemWidget(title = "이벤트")
}
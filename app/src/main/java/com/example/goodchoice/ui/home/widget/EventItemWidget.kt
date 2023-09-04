package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp15

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
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
    ) {
        Image(
            modifier = Modifier.size(dp15),
            painter = painterResource(id = R.drawable.ic_brightness),
            colorFilter = ColorFilter.tint(Theme.colorScheme.yellow),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun previewEventItemWidget() {
    EventItemWidget(title = "이벤트")
}
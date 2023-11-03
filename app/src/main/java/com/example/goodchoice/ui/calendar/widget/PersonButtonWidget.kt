package com.example.goodchoice.ui.calendar.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.theme.*

/**
 * 해외 여행 캘린더
 */
@Composable
fun PersonButtonWidget(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.5.dp,
    containerColor: Color = Theme.colorScheme.white,
    contentColor: Color = Theme.colorScheme.darkGray,
    borderColor: Color = Color.Transparent,
    leftText: String = "",
    rightText: String = "",
    onItemClick: () -> Unit = {},
) {

    CardWidget(
        modifier = modifier,
        cornerShape = RoundedCornerShape(dp20),
        containerColor = containerColor,
        borderColor = borderColor,
        borderWidth = borderWidth,
        innerPadding = PaddingValues(horizontal = dp30, vertical = dp15),
        alignment = Alignment.TopStart,
        onItemClick = { onItemClick() }) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .padding(end = dp8),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(dp15),
                    painter = painterResource(id = R.drawable.ic_hotel),
                    colorFilter = ColorFilter.tint(contentColor),
                    contentDescription = null
                )
            }

            TextWidget(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .padding(end = dp8),
                text = leftText,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .padding(end = dp8),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(dp15),
                    painter = painterResource(id = R.drawable.ic_nav_my_page),
                    colorFilter = ColorFilter.tint(contentColor),
                    contentDescription = null
                )
            }
            TextWidget(
                modifier = Modifier
                    .weight(1f, fill = false),
                text = rightText,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                textAlign = TextAlign.Center
            )
        }
    }
}
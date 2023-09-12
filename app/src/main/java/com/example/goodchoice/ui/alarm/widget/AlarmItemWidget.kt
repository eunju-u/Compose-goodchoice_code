package com.example.goodchoice.ui.alarm.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.R
import com.example.goodchoice.api.data.AlarmItem
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp15

@Composable
fun AlarmItemWidget(item: AlarmItem) {
    ButtonWidget(onItemClick = { }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.ic_volume_up), contentDescription = null,
                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = dp15),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TextWidget(
                    text = "${item.title}",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = Theme.colorScheme.gray
                )

                TextWidget(
                    text = "${item.content}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Theme.colorScheme.gray,
                )

                TextWidget(
                    text = "${item.date}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Theme.colorScheme.gray
                )
            }
        }
    }
}
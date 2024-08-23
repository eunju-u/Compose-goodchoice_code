package com.example.ui.calendar.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.ui.R
import com.example.ui.theme.Theme
import com.example.ui.theme.*
import com.example.ui.components.ShapeButton

@Composable
fun PersonWidget(
    count: Int = 0,
    max: Int = 0,
    isKid: Boolean = false, // 아동은 0명부터 start
    title: String = "",
    description: String = "",
    onLeftItemClick: () -> Unit = {},
    onRightItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dp25, bottom = dp25),
        horizontalArrangement = Arrangement.spacedBy(dp10)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.padding(bottom = dp3),
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(dp25),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val min = if(isKid) 0 else 1
            ShapeButton(
                size = dp40,
                isChecked = count > min,
                checkedColor = if (count > min) Theme.colorScheme.pureBlue else Theme.colorScheme.pureGray,
                borderColor = if (count > min) com.example.ui.theme.Theme.colorScheme.blue1 else com.example.ui.theme.Theme.colorScheme.gray,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_remove),
                        colorFilter = ColorFilter.tint(if (count > min) com.example.ui.theme.Theme.colorScheme.blue1 else com.example.ui.theme.Theme.colorScheme.gray),
                        contentDescription = "빼기",
                    )
                },
                onItemClick = onLeftItemClick
            )
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            ShapeButton(
                size = com.example.ui.theme.dp40,
                isChecked = count <= max,
                checkedColor = if (count < max) com.example.ui.theme.Theme.colorScheme.pureBlue else com.example.ui.theme.Theme.colorScheme.pureGray,
                borderColor = if (count < max) com.example.ui.theme.Theme.colorScheme.blue1 else com.example.ui.theme.Theme.colorScheme.gray,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_add),
                        colorFilter = ColorFilter.tint(if (count < max) com.example.ui.theme.Theme.colorScheme.blue1 else com.example.ui.theme.Theme.colorScheme.gray),
                        contentDescription = "더하기",
                    )
                },
                onItemClick = onRightItemClick
            )
        }
    }
}
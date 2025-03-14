package com.example.my_info.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ui_common.R
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.TextWidget
import com.example.ui_theme.*

@Composable
fun CouponWidget(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
) {
    CardWidget(
        innerPadding = PaddingValues(dp0),
        borderColor = Theme.colorScheme.pureGray,
        cornerShape = RoundedCornerShape(dp10),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextWidget(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onLeftClick() }
                        .padding(top = dp10, bottom = dp10),
                    text = stringResource(id = R.string.str_point),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    color = Theme.colorScheme.gray
                )

                VerticalDivider(
                    modifier = Modifier
                        .width(dp1).height(dp15),
                    color = Theme.colorScheme.pureGray
                )
                TextWidget(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onRightClick() }
                        .padding(top = dp10, bottom = dp10),
                    text = stringResource(id = R.string.str_coupon),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    color = Theme.colorScheme.gray
                )
            }
        }
    )
}
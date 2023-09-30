package com.example.goodchoice.ui.stayDetail.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.goodchoice.ui.components.TagWidget
import com.example.goodchoice.R
import com.example.goodchoice.api.data.PayData
import com.example.goodchoice.ui.components.CardWidget
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil

@Composable
fun PayWidget(data: PayData = PayData()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dp10, bottom = dp10)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TagWidget(
                title = stringResource(id = R.string.str_benefit),
                containerColor = Theme.colorScheme.lightPink,
                contentColor = Theme.colorScheme.red,
                shape = dp5,
            )
            Text(
                modifier = Modifier.padding(start = dp10),
                text = data.payName ?: "",
                color = Theme.colorScheme.darkGray,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(dp10))
        CardWidget(modifier = Modifier.fillMaxWidth(),
            innerPadding = PaddingValues(dp15),
            cornerShape = RoundedCornerShape(dp8),
            containerColor = Theme.colorScheme.pureGray,
            content = {
                data.payInfoList?.let { list ->
                    Column {
                        list.forEach { item ->
                            Text(
                                text = StringUtil.setTextLine(
                                    originText = item.payInfo ?: "",
                                    targetText = item.payLineTest ?: ""
                                ),
                                color = Theme.colorScheme.gray,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

            }
        )
    }
}
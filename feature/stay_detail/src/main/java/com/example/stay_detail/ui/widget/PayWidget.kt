package com.example.stay_detail.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.Const
import com.example.ui_common.R
import com.example.ui_common.components.CardWidget
import com.example.stay_detail.domain.model.PayData
import com.example.stay_detail.domain.model.PayItem
import com.example.ui_common.components.TagWidget
import com.example.ui_common.utils.StringUtil
import com.example.ui_theme.*

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
                innerPadding = PaddingValues(dp5)
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

@Preview
@Composable
fun PreviewPayWidget() {
    PayWidget(
        PayData(
            payType = Const.PAY_TOSS,
            payName = "토스페이",
            payInfoList = listOf(
                PayItem(
                    payLineTest = "최대 1만원 할인",
                    payInfo = "3만원 이상, 10% 최대 1만원 할인(오전 10시, 일450명)"
                ),
                PayItem(
                    payLineTest = "2천원 할인",
                    payInfo = "2만원 이상, 2천원 할인 (오후 4시, 일 1,500명)"
                ),
                PayItem(
                    payLineTest = "5천원 캐시백",
                    payInfo = "+생애 첫결제 시, 5천원 캐시백"
                )
            )
        )
    )
}
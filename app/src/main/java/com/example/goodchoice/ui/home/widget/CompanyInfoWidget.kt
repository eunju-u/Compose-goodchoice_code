package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.ui.R

@Composable
fun CompanyInfoWidget() {
    val textStyle = MaterialTheme.typography.labelSmall
    val textColor = Theme.colorScheme.gray
    val paddingModifier = Modifier.padding(start = dp15, end = dp15)

    var isShowInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(paddingModifier)
    ) {
        SpaceBetweenRowWidget(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.str_app_name),
            textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            textColor = textColor,
        ) {
            Row(
                modifier = Modifier.clickable {
                    isShowInfo = !isShowInfo
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.str_business_info),
                    color = Theme.colorScheme.gray,
                    style = textStyle
                )
                Spacer(modifier = Modifier.width(dp5))
                Image(
                    modifier = Modifier.size(dp15),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "arrow"
                )
            }
        }

        if (isShowInfo) {
            Spacer(modifier = Modifier.height(dp15))
            Text(
                text = stringResource(id = R.string.str_business_info_detail),
                color = textColor,
                style = textStyle
            )
        }

        Spacer(modifier = Modifier.height(dp15))
        Text(
            text = buildAnnotatedString {
                append("${stringResource(id = R.string.str_terms_of_use)} | ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(stringResource(id = R.string.str_privacy_policy))
                }
                append(" | ${stringResource(id = R.string.str_consumer_dispute_standards)}")
                append(" | ${stringResource(id = R.string.str_check_business_info)}")
                append(" | ${stringResource(id = R.string.str_content_business)}")
            }, color = textColor, style = textStyle
        )

        Spacer(modifier = Modifier.height(dp20))
        Text(
            text = stringResource(id = R.string.str_business_info_responsibility),
            color = textColor,
            style = textStyle
        )
    }
}
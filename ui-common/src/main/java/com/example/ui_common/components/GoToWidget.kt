package com.example.ui_common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_theme.*

@Composable
fun GoToWidget(
    modifier: Modifier = Modifier,
    firstText: String = "",
    secondText: String = "",
    thirdText: String = "",
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier,
            text = firstText,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = Theme.colorScheme.gray
        )
        Spacer(modifier = Modifier.height(dp10))
        Text(
            modifier = Modifier,
            text = secondText,
            style = MaterialTheme.typography.labelMedium,
            color = Theme.colorScheme.gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(dp10))

        Text(
            modifier = Modifier.clickable {
                onClick()
            },
            text = thirdText,
            style = MaterialTheme.typography.labelMedium,
            color = Theme.colorScheme.panoramaBlue
        )
    }
}

@Preview
@Composable
fun PreviewGoToWidget() {
    GoToWidget(
        firstText = "찜 목록을 볼 수 없어요",
        secondText = "로그인 후 찜한 상품을 확인하세요",
        thirdText = "로그인",
    )
}

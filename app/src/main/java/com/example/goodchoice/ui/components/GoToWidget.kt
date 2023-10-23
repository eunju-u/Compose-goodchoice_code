package com.example.goodchoice.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp10

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
        firstText = stringResource(id = R.string.str_no_see_like_list),
        secondText = stringResource(id = R.string.str_check_like_list_after_login),
        thirdText = stringResource(id = R.string.str_login),
    )
}

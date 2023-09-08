package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.goodchoice.ui.theme.dp30
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp10

@Composable
fun EmptyLikeWidget(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(start = dp30, end = dp30),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.str_no_see_like_list),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = Theme.colorScheme.gray
        )
        Spacer(modifier = Modifier.height(dp10))
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.str_check_like_list_after_login),
            style = MaterialTheme.typography.labelMedium,
            color = Theme.colorScheme.gray
        )
        Spacer(modifier = Modifier.height(dp10))

        Text(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.str_login),
            style = MaterialTheme.typography.labelSmall,
            color = Theme.colorScheme.panoramaBlue
        )
    }

}
package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.goodchoice.ui.theme.dp30
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.GoLoginTextWidget

@Composable
fun LikeLoginWidget(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(start = dp30, end = dp30),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoLoginTextWidget(
            firstText = stringResource(id = R.string.str_no_see_like_list),
            secondText = stringResource(id = R.string.str_check_like_list_after_login)
        )
    }

}
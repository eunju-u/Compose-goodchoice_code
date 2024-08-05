package com.example.goodchoice.ui.like.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.common.theme.*
import com.example.common.R
import com.example.common.components.GoToWidget

@Composable
fun EmptyDataWidget(value: String = "", onClick: () -> Unit = {}) {
    GoToWidget(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = dp30, end = dp30),
        firstText = stringResource(
            id = R.string.str_no_like_list,
            value
        ),
        secondText = stringResource(
            id = R.string.str_search_like,
            value
        ),
        thirdText = stringResource(
            id = R.string.str_go_to_like,
            value
        ),
        onClick = onClick
    )
}
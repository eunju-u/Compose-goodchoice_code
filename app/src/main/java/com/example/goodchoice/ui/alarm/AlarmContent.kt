package com.example.goodchoice.ui.alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.GoLoginTextWidget
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp30

@Composable
fun AlarmContent(onFinish: () -> Unit = {}) {
    Box {
        Column {
            TopAppBarWidget(
                title = stringResource(id = R.string.str_alarm),
                isCloseButton = false,
                onFinish = { onFinish() })

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Theme.colorScheme.white)
                    .padding(start = dp30, end = dp30),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //TODO eunju : 로그인
                if (true) {
                    item {
                        GoLoginTextWidget(
                            firstText = stringResource(id = R.string.str_no_see_alarm),
                            secondText = stringResource(id = R.string.str_check_alarm_after_login)
                        )
                    }
                } else {


                }

            }
        }

    }
}
package com.example.goodchoice.ui.alarm

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.components.GoToWidget
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp30

@Composable
fun AlarmContent(viewModel: AlarmViewModel, onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val alarmUiState = viewModel.alarmUiState.collectAsStateWithLifecycle()

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
                if (alarmUiState.value is ConnectInfo.Available) {
                    if (!pref.isLogin()) {
                        item {
                            GoToWidget(
                                firstText = stringResource(id = R.string.str_no_see_alarm),
                                secondText = stringResource(id = R.string.str_check_alarm_after_login),
                                thirdText = stringResource(id = R.string.str_login),
                                onClick = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            LoginActivity::class.java
                                        )
                                    )
                                }
                            )
                        }
                    } else {
                    }
                }
            }
        }
    }
}
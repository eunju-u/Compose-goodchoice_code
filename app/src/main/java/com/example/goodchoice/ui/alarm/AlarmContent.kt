package com.example.goodchoice.ui.alarm

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.components.*
import com.example.common.R
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.alarm.widget.AlarmItemWidget
import com.example.goodchoice.ui.login.LoginActivity
import com.example.common.theme.Theme
import com.example.common.theme.*

@Composable
fun AlarmContent(viewModel: AlarmViewModel, onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val alarmUiState = viewModel.alarmUiState.collectAsStateWithLifecycle()
    var isShowDialog by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBarWidget(
                title = stringResource(id = R.string.str_alarm),
                isCloseButton = false,
                onFinish = { onFinish() })

            if (alarmUiState.value is AlarmConnectInfo.Available) {
                if (!pref.isLogin) {
                    GoToWidget(modifier = Modifier.fillMaxSize(),
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
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Theme.colorScheme.white)
                            .padding(start = dp20, end = dp20),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val data = (alarmUiState.value as AlarmConnectInfo.Available).data
                        //알림 리스트가 없는 경우
                        if (data.isEmpty()) {
                            item {
                                TextWidget(
                                    text = stringResource(id = R.string.str_no_alarm),
                                    style = MaterialTheme.typography.displaySmall,
                                    color = Theme.colorScheme.gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        //알림 리스트가 있는 경우
                        else {
                            items(items = data) { item ->
                                AlarmItemWidget(item)
                            }
                            item {
                                Spacer(modifier = Modifier.height(dp35))
                                TextWidget(
                                    text = stringResource(id = R.string.str_save_alarm_max),
                                    style = MaterialTheme.typography.displaySmall,
                                    color = Theme.colorScheme.gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }


        when (alarmUiState.value) {
            is AlarmConnectInfo.Loading -> LoadingWidget()
            is AlarmConnectInfo.Error -> isShowDialog = true
            else -> {}
        }
    }

    if (isShowDialog) {
        AlertDialogWidget(
            onDismiss = { isShowDialog = false },
            title = stringResource(id = R.string.str_dialog_network_not_connect),
            onConfirm = {
                isShowDialog = false
            },
            oneButtonText = stringResource(id = R.string.str_ok),
        )
    }
}
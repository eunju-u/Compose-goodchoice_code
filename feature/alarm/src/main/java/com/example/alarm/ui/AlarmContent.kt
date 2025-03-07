package com.example.alarm.ui

import android.content.Intent
import android.net.Uri
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
import com.example.alarm.ui.state.AlarmUiState
import com.example.ui_common.R
import com.example.alarm.ui.widget.AlarmItemWidget
import com.example.common.intent.CommonIntent
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.GoToWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.components.TextWidget
import com.example.ui_common.components.TopAppBarWidget
import com.example.ui_theme.*

@Composable
fun AlarmContent(viewModel: AlarmViewModel, onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)
    val alarmUiState by viewModel.alarmUiState.collectAsState()
    var isShowDialog by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBarWidget(
                title = stringResource(id = R.string.str_alarm),
                isCloseButton = false,
                onFinish = { onFinish() })

            if (alarmUiState is AlarmUiState.Success) {
                if (!pref.isLogin) {
                    GoToWidget(modifier = Modifier.fillMaxSize(),
                        firstText = stringResource(id = R.string.str_no_see_alarm),
                        secondText = stringResource(id = R.string.str_check_alarm_after_login),
                        thirdText = stringResource(id = R.string.str_login),
                        onClick = {
                            context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("feature://login")
                            })
                        }
                    )
                } else {
                    val list = (alarmUiState as AlarmUiState.Success).list

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Theme.colorScheme.white)
                            .padding(start = dp20, end = dp20),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //알림 리스트가 없는 경우
                        if (list.isEmpty()) {
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
                            items(items = list) { item ->
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

        if (isShowDialog) {
            AlertDialogWidget(
                onDismiss = { isShowDialog = false },
                title = stringResource(id = R.string.str_dialog_network_not_connect),
                onConfirm = {
                    isShowDialog = false
                    viewModel.sendIntent(CommonIntent.Retry("reload"))
                },
                oneButtonText = stringResource(id = R.string.str_ok),
            )
        }

        when (alarmUiState) {
            is AlarmUiState.Loading -> LoadingWidget()
            is AlarmUiState.Error -> isShowDialog = true
            else -> {}
        }
    }
}
package com.example.goodchoice.ui.alarm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.preference.GoodChoicePreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmViewModel : ViewModel() {
    var alarmUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    fun getAlarmData(context: Context) = viewModelScope.launch {
        val pref = GoodChoicePreference(context)
        alarmUiState.value = ConnectInfo.Loading

        if (pref.isLogin()) {
            withContext(coroutineContext) {
                delay(1000)
            }
        }
        alarmUiState.value = ConnectInfo.Available()
    }
}
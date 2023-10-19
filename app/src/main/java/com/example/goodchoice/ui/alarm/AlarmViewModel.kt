package com.example.goodchoice.ui.alarm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.api.data.AlarmItem
import com.example.goodchoice.preference.GoodChoicePreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmViewModel : ViewModel() {
    var alarmUiState = MutableStateFlow<AlarmConnectInfo>(AlarmConnectInfo.Init)

    fun requestAlarmData(context: Context) = viewModelScope.launch {
        val pref = GoodChoicePreference(context)
        alarmUiState.value = AlarmConnectInfo.Loading

        if (pref.isLogin) {
            withContext(coroutineContext) {
                delay(200)
            }
        }

        val data = listOf(
            AlarmItem(
                alarmIdx = 0,
                title = "알림함이 새롭게 단장되었어요!",
                content = "이제 예약부터 할인 * 혜택 소식까지 알림함에서 모두 확인 할 수 있어요",
                date = "2023-08-26"
            )
        )

        alarmUiState.value = AlarmConnectInfo.Available(data)
    }
}

sealed interface AlarmConnectInfo {
    object Init : AlarmConnectInfo
    object Loading : AlarmConnectInfo
    data class Available(val data: List<AlarmItem>) : AlarmConnectInfo
    data class Error(val message: String? = null) : AlarmConnectInfo
}
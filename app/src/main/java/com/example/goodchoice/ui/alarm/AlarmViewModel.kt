package com.example.goodchoice.ui.alarm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.data.dto.AlarmItem
import com.example.goodchoice.domain.usecase.AlarmUseCase
import com.example.goodchoice.preference.GoodChoicePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val useCase: AlarmUseCase) : ViewModel() {
    var alarmUiState = MutableStateFlow<AlarmConnectInfo>(AlarmConnectInfo.Init)

    fun requestAlarmData(context: Context) = viewModelScope.launch {
        val pref = GoodChoicePreference(context)
        alarmUiState.value = AlarmConnectInfo.Loading

        if (pref.isLogin) {
            withContext(coroutineContext) {
                delay(200)
            }
        }

        val data = useCase.getAlarmData()
        alarmUiState.value = data
    }
}

sealed interface AlarmConnectInfo {
    object Init : AlarmConnectInfo
    object Loading : AlarmConnectInfo
    data class Available(val data: List<AlarmItem>) : AlarmConnectInfo
    data class Error(val message: String? = null) : AlarmConnectInfo
}
package com.example.ui.alarm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.info.AlarmConnectInfo
import com.example.domain.usecase.AlarmUseCase
import com.example.data.local.preference.GoodChoicePreference
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

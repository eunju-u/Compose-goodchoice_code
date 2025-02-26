package com.example.alarm.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarm.domain.usecase.AlarmUseCase
import com.example.alarm.ui.state.AlarmUiState
import com.example.common.intent.CommonIntent
import com.example.data.local.preference.GoodChoicePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val useCase: AlarmUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _alarmUiState: MutableStateFlow<AlarmUiState> =
        MutableStateFlow(AlarmUiState.Loading)
    val alarmUiState: StateFlow<AlarmUiState> get() = _alarmUiState

    private val _intent = MutableSharedFlow<CommonIntent>()
    private val intent: SharedFlow<CommonIntent> get() = _intent

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.distinctUntilChanged() // 동일한 Intent 무시
            intent.collect { myInfoIntent ->
                when (myInfoIntent) {
                    is CommonIntent.LoadMyInfo -> loadAlarmData()
                    is CommonIntent.Retry -> loadAlarmData()
                }
            }
        }
    }

    private fun loadAlarmData() = viewModelScope.launch {
        val pref = GoodChoicePreference(context)

        if (pref.isLogin) {
            withContext(coroutineContext) {
                delay(200)
            }
        }

        useCase.getAlarmData()
            .onStart {
                _alarmUiState.value = AlarmUiState.Loading // 로딩
            }
            .catch { exception ->
                _alarmUiState.value = AlarmUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { stayItems ->
                _alarmUiState.value = AlarmUiState.Success(stayItems) // 성공
            }
    }

    fun sendIntent(intent: CommonIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }
}

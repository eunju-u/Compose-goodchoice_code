package com.example.my_info.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.my_info.domain.usecase.MyInfoUseCase
import com.example.my_info.ui.state.MyInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyInfoViewModel @Inject constructor(
    private val myInfoUseCase: MyInfoUseCase,
) : ViewModel() {

    private val _myInfoState: MutableStateFlow<MyInfoUiState> =
        MutableStateFlow(MyInfoUiState.Loading)
    val myInfoState: StateFlow<MyInfoUiState> get() = _myInfoState

    private val _intent = MutableSharedFlow<CommonIntent>()
    private val intent: SharedFlow<CommonIntent> get() = _intent

    var isShowDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var dialogType: MutableStateFlow<DialogType> = MutableStateFlow(DialogType.NONE)

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.distinctUntilChanged() // 동일한 Intent 무시
            intent.collect { myInfoIntent ->
                when (myInfoIntent) {
                    is CommonIntent.LoadMyInfo -> loadMyInfo()
                    is CommonIntent.Retry -> loadMyInfo()
                }
            }
        }
    }

    private suspend fun loadMyInfo() {
        myInfoUseCase.getMyInfoData()
            .onStart {
                _myInfoState.value = MyInfoUiState.Loading // 로딩
            }
            .catch { exception ->
                _myInfoState.value = MyInfoUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { stayItems ->
                _myInfoState.value = MyInfoUiState.Success(stayItems) // 성공
            }
    }

    fun sendIntent(intent: CommonIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }

}
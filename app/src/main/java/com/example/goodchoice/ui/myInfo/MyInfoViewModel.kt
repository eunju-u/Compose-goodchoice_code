package com.example.goodchoice.ui.myInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.info.ConnectInfo
import com.example.domain.model.MyInfoData
import com.example.domain.usecase.MyInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyInfoViewModel @Inject constructor(
    private val myInfoUseCase: MyInfoUseCase,
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    var myInfoData = MutableStateFlow(MyInfoData())

    fun requestMyInfoData() = viewModelScope.launch {
        homeUiState.value = ConnectInfo.Loading
        myInfoData.value = myInfoUseCase.getMyInfoData()
        homeUiState.value = ConnectInfo.Available()
    }
}
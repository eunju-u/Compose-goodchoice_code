package com.example.my_info.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.info.ConnectInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyInfoViewModel @Inject constructor(
    private val myInfoUseCase: com.example.my_info.domain.usecase.MyInfoUseCase,
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    var myInfoData = MutableStateFlow(com.example.my_info.domain.model.MyInfoData())

    fun requestMyInfoData() = viewModelScope.launch {
        homeUiState.value = ConnectInfo.Loading
        myInfoData.value = myInfoUseCase.getMyInfoData()
        homeUiState.value = ConnectInfo.Available()
    }
}
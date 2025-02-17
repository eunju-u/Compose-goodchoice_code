package com.example.goodchoice.ui.main

import androidx.lifecycle.ViewModel
import com.example.domain.info.ConnectInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    //현재 navi가 보고 있는 루트
    var currentRoute = MutableStateFlow("") //collect 사용 가능.

    /************/
    private val _isShowDialog = MutableStateFlow(false)
    val isShowDialog: MutableStateFlow<Boolean> = _isShowDialog

}
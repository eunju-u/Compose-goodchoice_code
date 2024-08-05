package com.example.goodchoice.ui.stayDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.DialogType
import com.example.goodchoice.data.dto.*
import com.example.goodchoice.domain.usecase.LikeUseCase
import com.example.goodchoice.domain.usecase.StayDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StayDetailViewModel @Inject constructor(
    private val useCase: StayDetailUseCase,
    private val likeUseCase: LikeUseCase
) : ViewModel() {
    //서버 찌를 경우 보낼 request 파라미터 값
    var stayItemId = ""

    //서버 찌를 경우 보낼 설정한 날짜 파라미터 값
    val settingDate = ""

    //intent 통해 받아오는 숙소 이름, TopAppBarWidget 에 노출하기 위함.
    //해당 viewModel 에서 가져오지 않는 이유 : 에러시 title 노출하려고 하는데
    //서버 조회 완료와 에러시 TopAppBar 가 다르기 때문에 두개를 만들어야 해서 하나로 통일하기 위함
    var stayItemTitle = ""

    var payList: List<PayData> = emptyList()

    var detailUiState = MutableStateFlow<StayDetailConnectInfo>(StayDetailConnectInfo.Init)

    var isLike = mutableStateOf(false)

    var isShowDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var dialogType: MutableStateFlow<DialogType> = MutableStateFlow(DialogType.NONE)

    fun requestStayDetail() = viewModelScope.launch {
        detailUiState.value = StayDetailConnectInfo.Loading

        val data = useCase.getDetailData(stayItemId)
        isLike.value = likeUseCase.hasLikeData(stayItemId)

        when (data) {
            is StayDetailConnectInfo.Available -> payList = data.data.payList ?: emptyList()
            is StayDetailConnectInfo.Error -> {
                isShowDialog.value = true
                dialogType.value = DialogType.NETWORK_ERROR
            }
            else -> {}
        }
        detailUiState.value = data
    }

    fun saveLike() = viewModelScope.launch {
        likeUseCase.insertLikeData(stayItemId)
    }
}

sealed interface StayDetailConnectInfo {
    object Init : StayDetailConnectInfo
    object Loading : StayDetailConnectInfo
    data class Available(val data: StayDetailData) : StayDetailConnectInfo
    data class Error(val message: String? = null) : StayDetailConnectInfo
}
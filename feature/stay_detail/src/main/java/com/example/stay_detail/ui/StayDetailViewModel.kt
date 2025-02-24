package com.example.stay_detail.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.domain.usecase.LikeUseCase
import com.example.stay_detail.domain.model.PayData
import com.example.stay_detail.domain.usecase.StayDetailUseCase
import com.example.stay_detail.ui.state.StayDetailUiState
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

    var isLike = mutableStateOf(false)

    var isShowDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var dialogType: MutableStateFlow<DialogType> = MutableStateFlow(DialogType.NONE)

    private val _stayDetailState: MutableStateFlow<StayDetailUiState> =
        MutableStateFlow(StayDetailUiState.Loading)
    val stayDetailUiState: StateFlow<StayDetailUiState> get() = _stayDetailState

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
                    is CommonIntent.LoadMyInfo -> loadMyInfo()
                    is CommonIntent.Retry -> loadMyInfo()
                }
            }
        }
    }

    private suspend fun loadMyInfo() {
        useCase.getDetailData(stayItemId)
            .onStart {
                _stayDetailState.value = StayDetailUiState.Loading // 로딩
            }
            .catch { exception ->
                _stayDetailState.value = StayDetailUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { stayItems ->
                _stayDetailState.value = StayDetailUiState.Success(stayItems) // 성공
            }
    }

    fun sendIntent(intent: CommonIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }

    fun requestStayDetail() = viewModelScope.launch {
        isLike.value = likeUseCase.hasLikeData(stayItemId)
    }

    fun saveLike() = viewModelScope.launch {
        likeUseCase.insertLikeData(stayItemId)
    }
}

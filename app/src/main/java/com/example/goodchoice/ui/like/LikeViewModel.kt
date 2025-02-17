package com.example.goodchoice.ui.like

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ToastUtil
import com.example.data.local.preference.GoodChoicePreference
import com.example.domain.info.ConnectInfo
import com.example.domain.model.StayItem
import com.example.domain.usecase.LikeUseCase
import com.example.ui_common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeUseCase: LikeUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    // 찜 > 국내 여행
    var koreaLikeData = listOf<StayItem>()

    // 찜 > 해외 여행
    var overseaLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 공간 대여
    var spaceRentalLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 레저 티켓
    var leisureLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    fun requestLikeData(context: Context) = viewModelScope.launch {
        val pref = GoodChoicePreference(context)
        homeUiState.value = ConnectInfo.Loading

        //로그인이 안되어 있는 경우 로딩 안돌게 함.
        // (이유 : 로그인 안된 화면에서는 로딩이 필요없고, 리스트가 필요할 경우만 로딩노출함.)
        if (pref.isLogin) {
            withContext(coroutineContext) {
                delay(200)
            }
        }
        koreaLikeData = likeUseCase.getLikeData()
        homeUiState.value = ConnectInfo.Available()
    }


    fun checkLikeData(stayId: String) = viewModelScope.launch {
        if (likeUseCase.hasLikeData(stayId)) {
            likeUseCase.deleteLikeData(stayId)
            ToastUtil.showToast(context, R.string.str_remove_like)
        } else {
            likeUseCase.insertLikeData(stayId)
            ToastUtil.showToast(context, R.string.str_add_like)
        }
    }
}
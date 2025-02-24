package com.example.like.ui

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ToastUtil
import com.example.data.local.preference.GoodChoicePreference
import com.example.domain.info.LikeUiState
import com.example.domain.intent.LikeIntent
import com.example.domain.model.StayItem
import com.example.domain.usecase.LikeUseCase
import com.example.ui_common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeUseCase: LikeUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // 찜 > 해외 여행
    var overseaLikeData = listOf<StayItem>()

    // 찜 > 공간 대여
    var spaceRentalLikeData = listOf<StayItem>()

    // 찜 > 레저 티켓
    var leisureLikeData = listOf<StayItem>()

    private val _likeUiState: MutableStateFlow<LikeUiState> =
        MutableStateFlow(LikeUiState.Loading)
    val likeUiState: StateFlow<LikeUiState> get() = _likeUiState

    fun handleIntents(intent: LikeIntent) {
        when (intent) {
            is LikeIntent.LoadLikeData -> loadLikeData()
            is LikeIntent.ToggleLike -> toggleLike(intent.stayId)
            is LikeIntent.Retry -> loadLikeData()
        }
    }

    private fun loadLikeData() = viewModelScope.launch {
        val pref = GoodChoicePreference(context)

        //로그인이 안되어 있는 경우 로딩 안돌게 함.
        // (이유 : 로그인 안된 화면에서는 로딩이 필요없고, 리스트가 필요할 경우만 로딩노출함.)
        if (pref.isLogin) {
            withContext(coroutineContext) {
                delay(200)
            }
        }

        likeUseCase.getLikeData()
            .onStart {
                _likeUiState.value = LikeUiState.Loading // 로딩
            }
            .catch { exception ->
                _likeUiState.value = LikeUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { stayItems ->
                _likeUiState.value = LikeUiState.Success(stayItems) // 성공
            }
    }

    private fun toggleLike(stayId: String) = viewModelScope.launch {
        try {
            if (likeUseCase.hasLikeData(stayId)) {
                likeUseCase.deleteLikeData(stayId)
                ToastUtil.showToast(context, R.string.str_remove_like)
            } else {
                likeUseCase.insertLikeData(stayId)
                ToastUtil.showToast(context, R.string.str_add_like)
            }
        } catch (e: Exception) {
            _likeUiState.value = LikeUiState.Error("Failed to update like status: ${e.message}")
        }
    }
}
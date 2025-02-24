package com.example.search.ui.detailSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.domain.model.KoreaSearchData
import com.example.search.domain.usecase.DetailSearchUseCase
import com.example.search.ui.state.DetailSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailSearchViewModel @Inject constructor(
    private val useCase: DetailSearchUseCase
) : ViewModel() {
    private val _detailSearchUiState = MutableStateFlow<DetailSearchUiState>(DetailSearchUiState.Loading)
    val detailSearchUiState: MutableStateFlow<DetailSearchUiState> = _detailSearchUiState

    private val _intent = MutableSharedFlow<CommonIntent>()
    private val intent: SharedFlow<CommonIntent> get() = _intent

    private val _searchData = MutableStateFlow<List<KoreaSearchData>>(listOf())
    val searchData = _searchData

    var isShowDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var dialogType: MutableStateFlow<DialogType> = MutableStateFlow(DialogType.NONE)

    private val _keyWord = MutableStateFlow("")
    val keyWord: MutableStateFlow<String> = _keyWord

    init {
        handleIntents() // Intent 핸들링 시작
        observeSearchKeyword() // 검색어 변경 감지
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intent.distinctUntilChanged() // 동일한 Intent 반복 방지
                .collect { myInfoIntent ->
                    when (myInfoIntent) {
                        is CommonIntent.LoadMyInfo -> loadSearchUiData()
                        is CommonIntent.Retry -> loadSearchUiData()
                    }
                }
        }
    }

    // 검색어를 감지하고 데이터 요청
    private fun observeSearchKeyword() {
        viewModelScope.launch {
            _keyWord.debounce(200) // 200ms 대기 후 조회 시작 (서버 조회 자주하지 않도록)
                .filter { it.isNotBlank() }
                .flatMapLatest { newWord -> requestResultSearchData(newWord) }  // 최신 검색어만 반영
                .catch { e -> _detailSearchUiState.value = DetailSearchUiState.Error(e.message ?: "Unknown error") }
                .collect { list -> _searchData.value = list }
        }
    }

    private fun loadSearchUiData() = viewModelScope.launch {
        useCase.getRankData()
            .onStart { _detailSearchUiState.value = DetailSearchUiState.Loading }
            .catch { exception ->
                _detailSearchUiState.value = DetailSearchUiState.Error(exception.message ?: "Unknown error") //에러
            }
            .collect { result ->
                _detailSearchUiState.value = DetailSearchUiState.Success(result)
            }
    }

    private suspend fun requestResultSearchData(newKey: String = ""): Flow<List<KoreaSearchData>> {
        return if (newKey.isNotEmpty()) {
            useCase.getSearchData()
                .map { list -> list.filter { it.nameToKey.contains(newKey) } } // ✅ Flow 내부에서 필터링
        } else {
            flowOf(emptyList()) // 빈 검색어일 경우 빈 리스트 반환
        }
    }

    fun onWordChanged(newWord: String) {
        _keyWord.value = newWord
    }

    fun sendIntent(intent: CommonIntent) {
        viewModelScope.launch { _intent.emit(intent) }
    }
}
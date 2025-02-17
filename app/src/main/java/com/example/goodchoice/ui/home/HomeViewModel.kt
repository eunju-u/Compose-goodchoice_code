package com.example.goodchoice.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.info.ConnectInfo
import com.example.domain.model.StayItem
import com.example.domain.usecase.RecentUseCase
import com.example.domain.model.CategoryItem
import com.example.domain.model.HomeData
import com.example.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val recentUseCase: RecentUseCase,
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)
    var homeData = MutableStateFlow(HomeData())
    val allCategoryList = LinkedList<CategoryItem>()

    //스플래쉬에서 메인화면으로 처음 진입시 플래그 설정
    var firstSplash = false

    //fullHeader 가 있는 상태 에서 navigation 이동시 유지 되도록 하는 플래그
    var isShowFullHeader = mutableStateOf(false) //collect 사용 불가능.

    // 최근 본 상품
    var recentDbData = listOf<StayItem>()

    fun requestHomeData(isRefresh: Boolean = false) = viewModelScope.launch {
        // SwipeRefresh 할 경우 1초 딜레이 줘서 상단 리스트 보여지게 함.
        // SwipeRefresh 할 경우 로딩 화면 나오지 않게 함.(상단 리스트가 노출 되어야 함.)
        if (isRefresh) {
            delay(1000)
        } else {
            homeUiState.value = ConnectInfo.Loading
        }

        val testHomeData = homeUseCase.getHomeData()

        allCategoryList.clear()
        testHomeData.categoryList?.map {
            it.categoryList?.map { item ->
                allCategoryList.add(item)
            }
        }
        homeData.value = testHomeData
        homeUiState.value = ConnectInfo.Available()

        if (firstSplash) {
            firstSplash = false
        }
    }

    /** DB 에서 가져온 최근 본 상품 리스트 **/
    fun recentDb() = viewModelScope.launch {
        recentDbData = recentUseCase.getList()
    }

}
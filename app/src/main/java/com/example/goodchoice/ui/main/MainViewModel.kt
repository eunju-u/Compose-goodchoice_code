package com.example.goodchoice.ui.main

import android.content.Context
import com.example.goodchoice.R
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.Const
import com.example.goodchoice.RoomType
import com.example.goodchoice.ConnectInfo
import com.example.goodchoice.MainBottomSheetType
import com.example.goodchoice.data.dto.RecommendAreaData
import com.example.goodchoice.data.dto.*
import com.example.goodchoice.db.recent.RecentDb
import com.example.goodchoice.domain.usecase.*
import com.example.goodchoice.mapper.generateData
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.main.nav.NavItem
import com.example.goodchoice.ui.search.data.KoreaSearchData
import com.example.goodchoice.utils.ToastUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

//(Map으로 관리 하려 했지만, 서버에서 받아오는 내용이 아니기 때문에 select 된 아이템을 유지하기 어려워 사용 하지 않음.)
data class AroundFilterSelectedData(
    //주변 선택한 필터 > 필터는 list 로 하려다가 타입값 맞추기 위해 MutableState 로 사용
    var selectedFilter: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    //주변 선택한 필터 > 추천순
    var selectedRecommend: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    var selectedRoom: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    var selectedReservation: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
    var selectedPrice: MutableState<AroundFilterItem> = mutableStateOf(AroundFilterItem()),
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val searchUseCase: SearchUseCase,
    private val aroundUseCase: AroundUseCase,
    private val likeUseCase: LikeUseCase,
    private val myInfoUseCase: MyInfoUseCase,
    private val recentUseCase: RecentUseCase
) : ViewModel() {
    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)
    var homeData = MutableStateFlow(HomeData())
    val allCategoryList = LinkedList<CategoryItem>()

    //스플래쉬에서 메인화면으로 처음 진입시 플래그 설정
    var firstSplash = false

    //바텀 시트 타입
    var bottomSheetType = MutableStateFlow(MainBottomSheetType.NONE)

    //fullHeader 가 있는 상태 에서 navigation 이동시 유지 되도록 하는 플래그
    var isShowFullHeader = mutableStateOf(false) //collect 사용 불가능.

    //현재 navi가 보고 있는 루트
    var currentRoute = MutableStateFlow("") //collect 사용 가능.

    // 최근 본 상품
    var recentDbData = listOf<StayItem>()

    /** 찜 화면 **/
    // 찜 > 국내 여행
    var koreaLikeData = listOf<StayItem>()

    // 찜 > 해외 여행
    var overseaLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 공간 대여
    var spaceRentalLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 레저 티켓
    var leisureLikeData: SnapshotStateList<StayItem> = mutableStateListOf()
    /************/

    /** 검색 화면 **/
    // 검색 > 국내 숙소 (검색 순위)
    var koreaSearchRankData: List<FilterItem> = listOf()

    // 검색 > 레저 티켓 (추천 검색어)
    var leisureSearchWordData: List<FilterItem> = listOf()

    // 검색 > 레저 티켓 (추천 지역)
    var leisureSearchAreaData: List<RecommendAreaData> = listOf()
    /************/

    /** 주변 화면 **/
    //주변 서버 조회 시 state
    var filterList = MutableStateFlow(listOf(AroundFilterData()))
    var aroundFilterSelect = AroundFilterSelectedData()
    val selectRoomType = mutableStateOf(RoomType.SLEEP_ROOM)
    val selectSearchItem = mutableStateOf(KoreaSearchData())
    /************/

    /** 내 정보 화면 **/
    var myInfoData = MutableStateFlow(MyInfoData())

    /************/

    fun getCurrentViewData(context: Context) {
        when (currentRoute.value) {
            NavItem.Home.route -> {
                CoroutineScope(Dispatchers.IO).launch {
                    requestHomeData()
                    recentDb()
                }
            }
            NavItem.Search.route -> {
                requestSearchData()
            }
            NavItem.Around.route -> {
                requestAroundData(true)
            }
            NavItem.Like.route -> {
                requestLikeData(context)
            }
            NavItem.MyInfo.route -> {
                requestMyInfoData()
            }
        }
    }

    suspend fun requestHomeData(isRefresh: Boolean = false) =
        withContext(viewModelScope.coroutineContext) {

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

    // 검색 데이터 요청
    private fun requestSearchData() = viewModelScope.launch {
        homeUiState.value = ConnectInfo.Loading

        // 국내숙소 > 검색 순위
        koreaSearchRankData = searchUseCase.getKoreaRankData()

        // 레저*티켓 > 추천검색어 서버 조회
        leisureSearchWordData = searchUseCase.getRecommendWordData()

        leisureSearchAreaData = searchUseCase.getAreaData()

        homeUiState.value = ConnectInfo.Available()
    }

    // 주변 데이터 요청
    // 숙박, 대실 클릭 했을 때만 서버 조회.
    // 처음 진입시는 서버 조회 하지 않고 하드코딩된 내용 보여줌.
    // 왜냐하면 네트워크가 없는 상태에서 주변 화면 진입 할경우 필터 리스트가 보여지고 있음.
    fun requestAroundData(isFirstEnter: Boolean = false) = viewModelScope.launch {
        //숙박, 대실 클릭 했을 때만 전체 로딩 돈다. 처음 진입 시 로딩 하지 않음.
        if (!isFirstEnter) {
            homeUiState.value = ConnectInfo.Loading

            withContext(coroutineContext) {
                delay(200)
            }
        }

        //숙박에서 사용되는 필터
        val listSleepType = aroundUseCase.getSleepData()
        val listRentalType = aroundUseCase.getRentalData()

        filterList.value =
            if (selectRoomType.value == RoomType.SLEEP_ROOM) listSleepType else listRentalType

        filterList.value.find { it.type == Const.RECOMMEND }?.filterList?.let {
            //네비 메뉴 이동시 선택한 필터값 가지고 있어야 하며, 룸 타입(숙박, 대실) 클릭시 에만 초기화 필요하여 추가
            if (aroundFilterSelect.selectedRecommend.value.type.isNullOrEmpty()) {
                aroundFilterSelect.selectedRecommend.value = it.first()
            }
        }
        requestFilterStayData()
    }

    /**
     * 필터에 따른 숙소 데이터 조회
     */
    private fun requestFilterStayData() = viewModelScope.launch {
        // 서버에 request 할 파라미터는 filterList, RecommendList, roomTypeList, reservationList, priceList 로 List<String> 으로 들어간다고 가정
        // 필터는 filterList 로 해서 보내고, 나머지 추전순, 룸유형 등은 select 된 filter 리스트로 해서 보낸다.
        // TODO 서버 돌기

        // 필터에 맞는 숙소 리스트 구성 필요
        val list = listOf<StayItem>()
        homeUiState.value = ConnectInfo.Available(list)
    }

    private fun requestLikeData(context: Context) = viewModelScope.launch {
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
            ToastUtils.showToast(R.string.str_remove_like)
        } else {
            likeUseCase.insertLikeData(stayId)
            ToastUtils.showToast(R.string.str_add_like)
        }
    }

    fun requestMyInfoData() = viewModelScope.launch {
        homeUiState.value = ConnectInfo.Loading
        myInfoData.value = myInfoUseCase.getMyInfoData()
        homeUiState.value = ConnectInfo.Available()
    }

    /** DB 에서 가져온 최근 본 상품 리스트 **/
    fun recentDb() = viewModelScope.launch {
        recentDbData = recentUseCase.getList()
    }
}
package com.example.goodchoice.ui.main

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.RoomType
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.*
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.home.homeData.MutableRecentData
import com.example.goodchoice.ui.main.nav.NavItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

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

class MainViewModel : ViewModel() {

    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)
    var homeData = MutableStateFlow(HomeData())
    val allCategoryList = LinkedList<CategoryItem>()

    //스플래쉬에서 메인화면으로 처음 진입시 플래그 설정
    var firstSplash = false

    // 최근 본 상품
    var recentData: MutableState<MutableRecentData> = mutableStateOf(MutableRecentData())

    // 찜 > 국내 여행
    var koreaLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 해외 여행
    var overseaLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 공간 대여
    var spaceRentalLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 찜 > 레저 티켓
    var leisureLikeData: SnapshotStateList<StayItem> = mutableStateListOf()

    // 내 정보
    var myInfoData = MutableStateFlow(MyInfoData())

    //fullHeader 가 있는 상태 에서 navigation 이동시 유지 되도록 하는 플래그
    var isShowFullHeader = mutableStateOf(false)

    var currentRoute = MutableStateFlow("")

    //서버가 없어, 최근 본 상품 삭제 하기 위한 플래그
    //홈 처음 진입시 최근 본 상품 목록이 보여야 하고, 최근 목록 함에 있어야 하며, 전체 삭제시 리스트 삭제되어야 함.
    var isRemoveRecentList = false

    //주변 서버 조회 시 state
    var filterList = MutableStateFlow(listOf(AroundFilterData()))
    var aroundFilterSelect = AroundFilterSelectedData()

    val selectRoomType = mutableStateOf(RoomType.SLEEP_ROOM)

    fun getCurrentViewData(context: Context) {
        when (currentRoute.value) {
            NavItem.Home.route -> {
                CoroutineScope(Dispatchers.Default).launch {
                    requestHomeData()
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

            val testHomeData = HomeData(
                categoryList = listOf(
                    CategoryData(
                        countryType = Const.KOREA, categoryList = listOf(
                            CategoryItem(0, Const.C_PREMIUM_BLACK, R.drawable.img_premium),
                            CategoryItem(1, Const.C_MOTEL, R.drawable.img_bed),
                            CategoryItem(2, Const.C_HOTEL_AND_RESORT, R.drawable.img_hotel),
                            CategoryItem(3, Const.C_PENSION_AND_PULL_VILLA, R.drawable.img_pull),
                            CategoryItem(4, Const.C_HOUSE_AND_VILLA, R.drawable.img_house),
                            CategoryItem(5, Const.C_CAMPING_AND_GLAMPING, R.drawable.img_camping),
                            CategoryItem(6, Const.C_GUESTHOUSE, R.drawable.img_guest_house),
                            CategoryItem(7, Const.C_SPACE_RENTAL, R.drawable.img_hotel_inside),
                            CategoryItem(8, Const.C_KOREA_AIRPLANE, R.drawable.img_airplane),
                            CategoryItem(9, Const.C_RENTAL_CAR, R.drawable.img_car),
                            CategoryItem(10, Const.C_LEISURE_AND_TICKET, R.drawable.img_cablecar),
                            CategoryItem(11, Const.C_GOOD_FOOD, R.drawable.img_food)
                        )
                    ), CategoryData(
                        countryType = Const.OVERSEA, categoryList = listOf(
                            CategoryItem(
                                0, Const.C_OVERSEA_AIRPLANE, R.drawable.img_oversea_airplane
                            ),
                            CategoryItem(1, Const.C_OVERSEA_STAY, R.drawable.img_oversea_house),
                            CategoryItem(
                                2, Const.C_OVERSEA_AIRPLANE_AND_STAY, R.drawable.img_oversea
                            )
                        )
                    )
                ), bannerList = listOf(
                    BannerData(
                        title = "레저스포츠 할인대전",
                        image = "https://image.goodchoice.kr/images/cms/home_img/8134729c1da068f6261ee73b32494f23.png",
                        url = "https://www.goodchoice.kr/more/eventView/3363?page=0"
                    ),
                    BannerData(
                        title = "행복한 휴가는 어촌 바다로",
                        image = "https://image.goodchoice.kr/images/cms/home_img/8327c964adbafe1dc00e14daedd63636.png",
                        url = "https://www.goodchoice.kr/more/eventView/3348?page=0"
                    ),
                    BannerData(
                        title = "레저도 강원특별자치도",
                        image = "https://image.goodchoice.kr/images/cms/home_img/a246a171514f6ea554ce59c96ad0c713.jpg",
                        url = "https://www.goodchoice.kr/more/eventView/3307?page=0"
                    ),
                    BannerData(
                        title = "",
                        image = "https://image.goodchoice.kr/images/cms/home_img/3c13f7bf4ea5ef67729fa285aca7896f.jpg",
                        url = "https://www.goodchoice.kr/more/eventView/608?page=0"
                    ),
                ), stayList = listOf(
                    StayData(
                        type = Const.TODAY_HOTEL,
                        title = "오늘 체크인 호텔 특가",
                        stayList = listOf(
                            StayItem(
                                id = "s_1",
                                label = "호텔.리조트",
                                name = "양양 더 앤 리조트 호텔&스파",
                                star = "8.7",
                                commentCount = 906,
                                location = "양양군.주문진터미널",
                                discountPer = 50,
                                defaultPrice = "330000",
                                discountPrice = "166470",
                                level = "3성급",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2020/12/16/5fd9c03138e91.jpg"
                            ),
                            StayItem(
                                id = "s_2",
                                label = "호텔.리조트",
                                name = "[당일특가] 체스터톤스 호텔",
                                star = "8.9",
                                commentCount = 986,
                                location = "속초시 * 속초터미널",
                                discountPer = 7,
                                defaultPrice = "159000",
                                discountPrice = "148000",
                                level = "가족호텔",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2019/10/01/5d929ac78a245.jpg"
                            ),
                            StayItem(
                                id = "s_3",
                                label = "호텔.리조트",
                                name = "[당일특가] 스탠포드 호텔 서울",
                                star = "9.3",
                                commentCount = 1842,
                                location = "마포구.디지털미디어",
                                discountPer = 0,
                                defaultPrice = "129000",
                                discountPrice = "0",
                                level = "4성급",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2019/11/20/5dd4d28395866.jpg"
                            ),
                            StayItem(
                                id = "s_4",
                                label = "호텔.리조트",
                                name = "브릿지호텔 인천송도 (구 호텔 스카이파크 인천 송도)",
                                star = "9.1",
                                commentCount = 1118,
                                location = "연수구.인천대입구역",
                                discountPer = 60,
                                defaultPrice = "250000",
                                discountPrice = "다른 날짜 확인",
                                level = "아파트먼트",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2018/05/10/5af38b9510af6.jpg"
                            ),
                            StayItem(
                                id = "s_5",
                                label = "호텔.리조트",
                                name = "[당일특가] 평창 라마다 호텔&스위트 바이 윈덤",
                                star = "9.3",
                                commentCount = 1613,
                                location = "평창군 * 진부역",
                                discountPer = 77,
                                defaultPrice = "330000",
                                discountPrice = "76000",
                                level = "특급",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2022/09/22/632ba76ebd516.jpg"
                            ),
                            StayItem(
                                id = "s_6",
                                label = "호텔.리조트",
                                name = "[당일특가] 라한셀렉트 경주",
                                star = "9.4",
                                commentCount = 1208,
                                location = "경주시 * 경주보문관",
                                discountPer = 34,
                                defaultPrice = "500000",
                                discountPrice = "329000",
                                level = "블랙",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2021/06/01/60b5ce848d252.jpg"
                            ),
                            StayItem(
                                id = "s_7",
                                label = "호텔.리조트",
                                name = "[당일특가] 호메르스 호텔",
                                star = "8.9",
                                commentCount = 1796,
                                location = "수영구 * 광안리해수욕장",
                                discountPrice = "145000",
                                level = "3성급",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2021/04/21/607fc73ac65f7.jpg"
                            ),
                        ),
                    ),
                    StayData(
                        type = Const.HOT_HOTEL, title = "이번 주 HOT 인기 펜션", stayList = listOf(
                            StayItem(
                                id = "s_8",
                                label = "국내 숙소",
                                name = "태안 팜비치펜션",
                                star = "9.7",
                                commentCount = 308,
                                location = "청포대해변 앞",
                                discountPer = 0,
                                defaultPrice = "130000",
                                discountPrice = "125000",
                                level = "",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2021/01/05/5ff427a82d07c.jpg"
                            ),
                            StayItem(
                                id = "s_9",
                                label = "국내 숙소",
                                name = "태안 린더버그풀빌라",
                                star = "8.7",
                                commentCount = 124,
                                location = "태안빛축제 차량 2분",
                                discountPer = 6,
                                defaultPrice = "71000",
                                discountPrice = "66740",
                                level = "",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2021/04/01/6065228219f14.jpg"
                            ),
                            StayItem(
                                id = "s_10",
                                label = "국내 숙소",
                                name = "강화 오카소 애견펜션",
                                star = "10",
                                commentCount = 8,
                                location = "마니산 차량 21분",
                                discountPer = 6,
                                defaultPrice = "170000",
                                discountPrice = "159800",
                                level = "",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2022/05/23/628b30715115f.jpg"
                            ),
                            StayItem(
                                id = "s_11",
                                label = "국내 숙소",
                                name = "청도 더포레 풀빌라",
                                star = "9.3",
                                commentCount = 166,
                                location = "청도프로방스 차량 17분",
                                discountPer = 11,
                                defaultPrice = "179000",
                                discountPrice = "159847",
                                level = "풀빌라",
                                mainImage = "https://image.goodchoice.kr/resize_490x348/affiliate/2022/11/14/6371d0fec7763.jpg"
//                            message = "개별수영장 보일러 공사로 미온수(32~33도), 노천탕(40도) 온도유지 가능합니다.[]애견 동반이 가능한 객실을 보유하고 있습니다.[]전 객실 3층 독채로 프라이빗하게 이요하실 수 있고, ~12인까지 충분히 입실 가능한 신축 풀빌라입니다.",
//                            defaultMessage = "입실 : 15:00 | 퇴실 11:00[]20시 이후 입실 시 사전문의 (필수)[]무료 Wi-Fi[]전 객실 금연[]주차 가능",
//                            aroundInfo = "",
                            )
                        )
                    )
                ), overSeaCityList = listOf(
                    OverSeaCityItem(id = 11000, cityName = "오사카"),
                    OverSeaCityItem(id = 11001, cityName = "후쿠오카"),
                    OverSeaCityItem(id = 11002, cityName = "도쿄"),
                    OverSeaCityItem(id = 11003, cityName = "다낭"),
                    OverSeaCityItem(id = 11004, cityName = "교토"),
                    OverSeaCityItem(id = 11005, cityName = "싱가포르")
                ),
                overseaSpecialList = listOf(
                    OverseaSpecialItem(
                        name = "[후쿠오카/공동구매] 유후인 1일 버스투어 선물 + 에스페리아 하",
                        discountPer = 40,
                        defaultPrice = "499000",
                        discountPrice = "299000",
                        mainImage = "https://trvis.r10s.com/d/strg/ctrl/26/1efe58082ad07aaa17b942a27c9b0712e1b57b2a.47.9.26.3.jpg"
                    ),
                    OverseaSpecialItem(
                        name = "[후쿠오카/공동구매] 유후인 1일 버스투어 선물 + 에스페리아 하",
                        discountPer = 40,
                        defaultPrice = "579000",
                        discountPrice = "349000",
                        mainImage = "https://trvis.r10s.com/d/strg/ctrl/26/45d7ee606dbb0e3dafeff0fcf68c1eca32bf59d0.47.9.26.3.jpg"
                    ),
                    OverseaSpecialItem(
                        name = "[나트랑/공동구매] 래디슨블루 깜란 나트랑 3박5일: 오션뷰,조식",
                        discountPer = 45,
                        defaultPrice = "849000",
                        discountPrice = "469000",
                        mainImage = "https://pix8.agoda.net/hotelImages/8233088/-1/5a5adb757c11a2bdccda76ecddabed72.jpg?ca=9&ce=1&s=1024x768"
                    ),
                    OverseaSpecialItem(
                        name = "[푸꾸옥] 베스트웨스턴 프리미어 푸꾸옥 3박5일(1BED/2BED)",
                        discountPer = 29,
                        defaultPrice = "699000",
                        discountPrice = "499000",
                        mainImage = "https://pix8.agoda.net/hotelImages/21726874/0/3e3a7de5420f901660c6618586e18b58.jpg?ce=0&s=1024x768"
                    ),
                    OverseaSpecialItem(
                        name = "[다낭] 마카즈키 리조트 3박5일 : 조식, 워터파크 포함",
                        discountPer = 33,
                        defaultPrice = "749000",
                        discountPrice = "499000",
                        mainImage = "https://pix8.agoda.net/hotelImages/10789475/-1/3ca87a72ffeb70eadb2780301ffbdc87.jpg?ce=0&s=1024x768"
                    ),
                    OverseaSpecialItem(
                        name = "[보라카이] 카사필라 비치 리조트 3박 5일 : 조식포함",
                        discountPer = 32,
                        defaultPrice = "629000",
                        discountPrice = "429000",
                        mainImage = "https://pix8.agoda.net/hotelImages/2050044/-1/2044c64f66b018f944637be744a6b3fa.jpg?ca=10&ce=1&s=1024x768"
                    )
                )
            )
            delay(200)

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

    private fun requestSearchData() {

    }

    // 숙박, 대실 클릭 했을 때만 서버 조회.
    // 처음 진입시는 서버 조회 하지 않고 하드코딩된 내용 보여줌.
    // 왜냐하면 네트워크가 없는 상태에서 주변 화면 진입 할경우 필터 리스트가 보여지고 있음.
    fun requestAroundData(isFirstEnter: Boolean = false) = viewModelScope.launch {
        //숙박, 대실 클릭 했을 때만 전체 로딩 돈다. 처음 진입 시 로딩 하지 않음.
        if (!isFirstEnter) {
            homeUiState.value = ConnectInfo.Loading

            withContext(coroutineContext) {
                delay(400)
            }
        }

        //숙박에서 사용되는 필터
        val listSleepType = listOf(
            AroundFilterData(
                type = Const.FILTER,
                text = "필터",
                filterList = listOf(),
            ),
            AroundFilterData(
                type = Const.RECOMMEND, text = "추천순", filterList = listOf(
                    AroundFilterItem(type = Const.RECOMMEND, text = "추천순"),
                    AroundFilterItem(type = Const.DISTANCE, text = "거리순"),
                    AroundFilterItem(type = Const.HIGH_GRADE, text = "평점높은순"),
                    AroundFilterItem(type = Const.HIGH_REVIEW, text = "리뷰많은순"),
                    AroundFilterItem(type = Const.ROW_PRICE, text = "낮은가격순"),
                    AroundFilterItem(type = Const.HIGH_PRICE, text = "높은가격순")
                )
            ),
            AroundFilterData(
                type = Const.ROOM, text = "숙소유형", filterList = listOf(
                    AroundFilterItem(type = Const.MOTEL, text = "모텔"),
                    AroundFilterItem(type = Const.HOTEL_AND_RESORT, text = "호텔*리조트"),
                    AroundFilterItem(type = Const.PENSION, text = "펜션"),
                    AroundFilterItem(type = Const.HOUSE_AND_VILLA, text = "홈&빌라"),
                    AroundFilterItem(type = Const.CAMPING, text = "캠핑"),
                    AroundFilterItem(type = Const.GUESTHOUSE, text = "게하*한옥")
                )
            ),
            AroundFilterData(type = Const.RESERVATION, text = "예약가능", filterList = listOf()),
            AroundFilterData(
                type = Const.PRICE, text = "가격", filterList = listOf(
                    AroundFilterItem(type = Const.LESS_5, text = "~5만원"),
                    AroundFilterItem(type = Const.M5_L10, text = "5~10만원"),
                    AroundFilterItem(type = Const.M10_L15, text = "10만원~15만원"),
                    AroundFilterItem(type = Const.M15_L20, text = "15만원~20만원"),
                    AroundFilterItem(type = Const.M20_L25, text = "20만원~25만원"),
                    AroundFilterItem(type = Const.M25_L30, text = "25만원~30만원"),
                    AroundFilterItem(type = Const.MORE_30, text = "30만원 이상~")
                )
            )
        )

        val listRentalType = listOf(
            AroundFilterData(
                type = Const.FILTER,
                text = "필터",
                filterList = listOf(),
            ), AroundFilterData(
                type = Const.RECOMMEND, text = "추천순", filterList = listOf(
                    AroundFilterItem(type = Const.RECOMMEND, text = "추천순"),
                    AroundFilterItem(type = Const.DISTANCE, text = "거리순"),
                    AroundFilterItem(type = Const.HIGH_GRADE, text = "평점높은순"),
                    AroundFilterItem(type = Const.HIGH_REVIEW, text = "리뷰많은순")
                )
            )
        )

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
        if (pref.isLogin()) {
            withContext(coroutineContext) {
                delay(200)
            }
        }

        homeUiState.value = ConnectInfo.Available()
    }

    private fun requestMyInfoData() = viewModelScope.async {
        /*
        * viewModelScope
        * homeUiState
        * */
        homeUiState.value = ConnectInfo.Loading

        val test = MyInfoData(
            topMenuList = listOf(
                CategoryItem(id = 0, name = "최근 본 상품", icon = R.drawable.ic_myinfo_recent),
                CategoryItem(id = 1, name = "할인·혜택", icon = R.drawable.ic_myinfo_discount),
                CategoryItem(id = 2, name = "내 리뷰", icon = R.drawable.ic_myinfo_review),
                CategoryItem(id = 3, name = "알림함", icon = R.drawable.ic_myinfo_noti)
            ), menuList = listOf(
                MyMenuData(
                    id = 0, title = "예약내역", list = listOf(
                        MyMenuItem(id = 0, name = "국내 숙소"),
                        MyMenuItem(id = 1, name = "해외 숙소"),
                        MyMenuItem(id = 2, name = "공간대여"),
                        MyMenuItem(id = 3, name = "티켓"),
                        MyMenuItem(id = 4, name = "실시간렌터카"),
                        MyMenuItem(id = 5, name = "항공"),
                        MyMenuItem(id = 6, name = "항공+숙소 결합상품")
                    )
                ), MyMenuData(
                    id = 1, title = "고객센터", list = listOf(
                        MyMenuItem(id = 0, icon = R.drawable.ic_myinfo_question, name = "자주 묻는 질문"),
                        MyMenuItem(
                            id = 1,
                            icon = R.drawable.ic_myinfo_cs_message,
                            name = "1:1 카카오 문의",
                            subText = "오전9시 ~ 새벽3시"
                        ),
                        MyMenuItem(
                            id = 2,
                            icon = R.drawable.ic_myinfo_cs_call,
                            name = "고객행복센터 연결",
                            subText = "오전9시 ~ 새벽3시"
                        ),
                    )
                ),
                /**
                 * banner (?)
                 * */
                MyMenuData(
                    id = 2, path = "banner_path", list = listOf(
                        MyMenuItem(id = 0, name = "공지사항"),
                        MyMenuItem(id = 1, name = "여기어때 상품권 잔액조회"),
                        MyMenuItem(id = 2, name = "리서치 참여"),
                        MyMenuItem(id = 3, name = "기업 출장/복지 서비스 문의"),
                        MyMenuItem(id = 4, name = "트립홀릭 혜택 안내"),
                        MyMenuItem(id = 5, name = "엘리트 혜택 안내"),
                        MyMenuItem(id = 6, name = "비즈니스 혜택 안내"),
                        MyMenuItem(id = 7, name = "혜택 QR")
                    )
                ), MyMenuData(
                    id = 3, list = listOf(
                        MyMenuItem(id = 0, name = "설정")
                    )
                )
            )
        )
        delay(200)

        myInfoData.value = test
        homeUiState.value = ConnectInfo.Available()

    }
}
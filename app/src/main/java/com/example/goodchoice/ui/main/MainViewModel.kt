package com.example.goodchoice.ui.main

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.*
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.home.homeData.MutableRecentData
import com.example.goodchoice.ui.main.nav.NavItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class MainViewModel : ViewModel() {

    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)
    var homeData = MutableStateFlow(HomeData())
    val allCategoryList = LinkedList<CategoryItem>()

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
    var isShowFullHeader = MutableStateFlow(false)

    var currentRoute = MutableStateFlow("")

    //서버가 없어, 최근 본 상품 삭제 하기 위한 플래그
    //홈 처음 진입시 최근 본 상품 목록이 보여야 하고, 최근 목록 함에 있어야 하며, 전체 삭제시 리스트 삭제되어야 함.
    var isRemoveRecentList = false

    //찜 서버 조회 시 state
    var likeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    fun getCurrentViewData(context: Context) {
        when (currentRoute.value) {
            NavItem.Home.route -> {
                CoroutineScope(Dispatchers.Default).launch {
                    requestHomeData().await()
                }
            }
            NavItem.Search.route -> {
                requestSearchData()
            }
            NavItem.Like.route -> {
                requestLikeData(context)
            }
            NavItem.MyInfo.route -> {
                requestMyInfoData()
            }
        }
    }

    fun requestHomeData() = viewModelScope.async {
        homeUiState.value = ConnectInfo.Loading
        val testHomeData = HomeData(
            categoryList = listOf(
                CategoryData(
                    countryType = Const.KOREA,
                    categoryList = listOf(
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
                ),
                CategoryData(
                    countryType = Const.OVERSEA,
                    categoryList = listOf(
                        CategoryItem(0, Const.C_OVERSEA_AIRPLANE, R.drawable.img_oversea_airplane),
                        CategoryItem(1, Const.C_OVERSEA_STAY, R.drawable.img_oversea_house),
                        CategoryItem(2, Const.C_OVERSEA_AIRPLANE_AND_STAY, R.drawable.img_oversea)
                    )
                )
            ),
            bannerList = listOf(
                BannerData(R.drawable.bg_purple),
                BannerData(R.drawable.bg_yellow),
                BannerData(R.drawable.bg_teal)
            ),
            stayList = listOf(
                StayData(
                    type = Const.TODAY_HOTEL,
                    title = "오늘 체크인 호텔 특가",
                    stayList = listOf(
                        StayItem(
                            label = "호텔.리조트",
                            name = "양양 더 앤 리조트 호텔&스파",
                            star = "8.7",
                            commentCount = 906,
                            location = "양양군.주문진터미널",
                            discountPer = 50,
                            defaultPrice = "330000",
                            discountPrice = "166470",
                            level = "3성급",
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 프밀리 풀빌라",
                            star = "9.4",
                            commentCount = 885,
                            location = "거제시.와현해수욕장",
                            discountPer = 6,
                            defaultPrice = "59000",
                            discountPrice = "55460",
                            level = "1성급",
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 스탠포드 호출 서울",
                            star = "9.3",
                            commentCount = 1842,
                            location = "마포구.디지털미디어",
                            discountPer = 0,
                            defaultPrice = "129000",
                            discountPrice = "0",
                            level = "레지던스",
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "브릿지호텔 인천송도 (구 호텔 스카이파크 인천 송도)",
                            star = "9.1",
                            commentCount = 1118,
                            location = "연수구.인천대입구역",
                            discountPer = 60,
                            defaultPrice = "250000",
                            discountPrice = "다른 날짜 확인",
                            level = "아파트먼트",
                        ),
                    ),
                ),
                StayData(
                    type = Const.HOT_HOTEL,
                    title = "오늘 HOT 인기 펜션",
                    stayList = listOf(
                        StayItem(
                            label = "국내 숙소",
                            name = "태안 팜비치펜션",
                            star = "9.7",
                            commentCount = 308,
                            location = "청포대해변 앞",
                            discountPer = 0,
                            defaultPrice = "130000",
                            discountPrice = "1250000",
                            level = "아파트먼트",
                            imageList = listOf(
//                                "https://image.goodchoice.kr/resize_490x348x1/affiliate/2021/01/05/5ff428bb363f5.jpg",
//                                "https://image.goodchoice.kr/resize_490x348/affiliate/2021/01/05/5ff426fa3495a.jpg",
//                                "https://image.goodchoice.kr/resize_490x348/affiliate/2021/01/05/5ff427a82d07c.jpg",
                            )
                        ),
                        StayItem(
                            label = "국내 숙소",
                            name = "태안 린더버그풀빌라",
                            star = "8.7",
                            commentCount = 124,
                            location = "태안빛축제 차량 2분",
                            discountPer = 6,
                            defaultPrice = "71000",
                            discountPrice = "66740",
                            level = "아파트먼트",
                        ),
                        StayItem(
                            label = "국내 숙소",
                            name = "청도 더포레 풀빌라",
                            star = "9.3",
                            commentCount = 166,
                            location = "청도프로방스 차량 17분",
                            discountPer = 11,
                            defaultPrice = "179000",
                            discountPrice = "159847",
                            level = "풀빌라",
                            message = "개별수영장 보일러 공사로 미온수(32~33도), 노천탕(40도) 온도유지 가능합니다.[]애견 동반이 가능한 객실을 보유하고 있습니다.[]전 객실 3층 독채로 프라이빗하게 이요하실 수 있고, ~12인까지 충분히 입실 가능한 신축 풀빌라입니다.",
                            defaultMessage = "입실 : 15:00 | 퇴실 11:00[]20시 이후 입실 시 사전문의 (필수)[]무료 Wi-Fi[]전 객실 금연[]주차 가능",
                            aroundInfo = "",
                            service = listOf(
                                Const.WIFI,
                                Const.FULL,
                                Const.SPA,
                                Const.COOKING,
                                Const.BBQ,
                                Const.COOKER,
                                Const.TV,
                                Const.BATHROOM,
                                Const.FREE_PARKING,
                                Const.AIR_CONDITIONER,
                                Const.REFRIGERATOR,
                                Const.SHOWER_ROOM,
                                Const.NO_SMOKING,
                                Const.ANIMAL,
                                Const.DRYER,
                                Const.CARD,
                                Const.PARKING_LOT,
                                Const.COOKING_POSSIBLE,
                                Const.MICROWAVE
                            )
                        )
                    )
                )
            ),
            overSeaCityList = listOf(
                OverSeaCityItem(id = 11000, cityName = "오사카"),
                OverSeaCityItem(id = 11001, cityName = "후쿠오카"),
                OverSeaCityItem(id = 11002, cityName = "도쿄"),
                OverSeaCityItem(id = 11003, cityName = "다낭"),
                OverSeaCityItem(id = 11004, cityName = "교토"),
                OverSeaCityItem(id = 11005, cityName = "싱가포르")
            )
        )
        delay(1000)

        allCategoryList.clear()
        testHomeData.categoryList?.map {
            it.categoryList?.map { item ->
                allCategoryList.add(item)
            }
        }
        homeData.value = testHomeData
        homeUiState.value = ConnectInfo.Available()

    }

    private fun requestSearchData() {

    }

    private fun requestLikeData(context: Context) = viewModelScope.launch {
        val pref = GoodChoicePreference(context)
        likeUiState.value = ConnectInfo.Loading

        //로그인이 안되어 있는 경우 로딩 안돌게 함.
        // (이유 : 로그인 안된 화면에서는 로딩이 필요없고, 리스트가 필요할 경우만 로딩노출함.)
        if (pref.isLogin()) {
            withContext(coroutineContext) {
                delay(1000)
            }
        }

        likeUiState.value = ConnectInfo.Available()
    }

    private fun requestMyInfoData() = viewModelScope.async{
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
            ),
            menuList = listOf(
                MyMenuData(
                    id = 0,
                    title = "예약내역",
                    list = listOf(
                        MyMenuItem(id = 0, name = "국내 숙소"),
                        MyMenuItem(id = 1, name = "해외 숙소"),
                        MyMenuItem(id = 2, name = "공간대여"),
                        MyMenuItem(id = 3, name = "티켓"),
                        MyMenuItem(id = 4, name = "실시간렌터카"),
                        MyMenuItem(id = 5, name = "항공"),
                        MyMenuItem(id = 6, name = "항공+숙소 결합상품")
                    )
                ),
                MyMenuData(
                    id = 1,
                    title = "고객센터",
                    list = listOf(
                        MyMenuItem(id = 0, icon = R.drawable.ic_myinfo_question, name = "자주 묻는 질문"),
                        MyMenuItem(id = 1, icon = R.drawable.ic_myinfo_cs_message,name = "1:1 카카오 문의", subText = "오전9시 ~ 새벽3시"),
                        MyMenuItem(id = 2, icon = R.drawable.ic_myinfo_cs_call, name = "고객행복센터 연결", subText = "오전9시 ~ 새벽3시"),
                    )
                ),
                /**
                 * banner (?)
                 * */
                MyMenuData(
                    id = 2,
                    path = "banner_path",
                    list = listOf(
                        MyMenuItem(id = 0, name = "공지사항"),
                        MyMenuItem(id = 1, name = "여기어때 상품권 잔액조회"),
                        MyMenuItem(id = 2, name = "리서치 참여"),
                        MyMenuItem(id = 3, name = "기업 출장/복지 서비스 문의"),
                        MyMenuItem(id = 4, name = "트립홀릭 혜택 안내"),
                        MyMenuItem(id = 5, name = "엘리트 혜택 안내"),
                        MyMenuItem(id = 6, name = "비즈니스 혜택 안내"),
                        MyMenuItem(id = 7, name = "혜택 QR")
                    )
                ),
                MyMenuData(
                    id = 3,
                    list = listOf(
                        MyMenuItem(id = 0, name = "설정")
                    )
                )
            )
        )
        delay(1000)

        myInfoData.value = test
        homeUiState.value = ConnectInfo.Available()

    }
}
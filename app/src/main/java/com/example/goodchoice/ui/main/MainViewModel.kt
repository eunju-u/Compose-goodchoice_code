package com.example.goodchoice.ui.main

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
import com.example.goodchoice.ui.home.homeData.MutableRecentData
import com.example.goodchoice.ui.main.nav.NavItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {

    var homeUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)
        private set
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

    //fullHeader 가 있는 상태 에서 navigation 이동시 유지 되도록 하는 플래그
    var isShowFullHeader = MutableStateFlow(false)

    var currentRoute = MutableStateFlow("")
    var isRefreshHomeData = MutableStateFlow(false)

    //서버가 없어, 최근 본 상품 삭제 하기 위한 플래그
    //홈 처음 진입시 최근 본 상품 목록이 보여야 하고, 최근 목록 함에 있어야 하며, 전체 삭제시 리스트 삭제되어야 함.
    var isRemoveRecentList = false
    fun getCurrentViewData() {
        when (currentRoute.value) {
            NavItem.Home.route -> {
                requestHomeData()
            }
            NavItem.Search.route -> {
                requestSearchData()
            }
            NavItem.Like.route -> {
                requestLikeData()
            }
            NavItem.MyInfo.route -> {
                requestMyInfoData()
            }
        }
    }

    private fun requestHomeData() = viewModelScope.launch {
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

        //홈 화면 재로드 플래그 초기화
        if (isRefreshHomeData.value) {
            isRefreshHomeData.value = false
        }
    }

    private fun requestSearchData() {

    }

    private fun requestLikeData() {

    }

    private fun requestMyInfoData() {

    }
}
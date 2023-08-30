package com.example.goodchoice.ui.main

import androidx.lifecycle.ViewModel
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class MainViewModel : ViewModel() {

    val homeData = MutableStateFlow(HomeData())
    val allCategoryList = LinkedList<CategoryItem>()

    fun requestHomeData() {
        val testHomeData = HomeData(
            categoryList = listOf(
                CategoryData(
                    countryType = Const.KOREA,
                    categoryList = listOf(
                        CategoryItem(0, "프리미엄블랙", R.drawable.ic_airplane),
                        CategoryItem(1, "모텔", R.drawable.ic_airplane),
                        CategoryItem(2, "호텔*리조트", R.drawable.ic_airplane),
                        CategoryItem(3, "펜션*풀빌라", R.drawable.ic_airplane),
                        CategoryItem(4, "홈&빌라", R.drawable.ic_airplane),
                        CategoryItem(5, "캠핑*글램핑", R.drawable.ic_airplane),
                        CategoryItem(6, "게하*한옥", R.drawable.ic_airplane),
                        CategoryItem(7, "공간대여", R.drawable.ic_airplane),
                        CategoryItem(8, "국내 항공", R.drawable.ic_airplane),
                        CategoryItem(9, "렌터카", R.drawable.ic_airplane),
                        CategoryItem(10, "레저*티켓", R.drawable.ic_airplane),
                        CategoryItem(11, "맛집", R.drawable.ic_airplane)
                    )
                ),
                CategoryData(
                    countryType = Const.OVERSEA,
                    categoryList = listOf(
                        CategoryItem(0, "해외 항공", R.drawable.ic_airplane),
                        CategoryItem(1, "해외 숙소", R.drawable.ic_airplane),
                        CategoryItem(2, "항공+숙소", R.drawable.ic_airplane)
                    )
                )
            ),
            bannerList = listOf(
                BannerData(R.drawable.shape_purple),
                BannerData(R.drawable.shape_yellow),
                BannerData(R.drawable.shape_teal)
            ),
            stayList = listOf(
                StayData(
                    type = Const.TODAY_HOTEL,
                    title = "오늘 체크인 호텔 특가",
                    listOf(
                        StayItem(
                            label = "호텔.리조트",
                            name = "양양 더 앤 리조트 호텔&스파",
                            star = "8.7",
                            commentCount = 906,
                            location = "양양군.주문진터미널",
                            discountPer = 50,
                            defaultPrice = 330000,
                            discountPrice = 166470
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 프밀리 풀빌라",
                            star = "9.4",
                            commentCount = 885,
                            location = "거제시.와현해수욕장",
                            discountPer = 6,
                            defaultPrice = 59000,
                            discountPrice = 55460
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "[당일특가] 스탠포드 호출 서울",
                            star = "9.3",
                            commentCount = 1842,
                            location = "마포구.디지털미디어",
                            discountPer = 0,
                            defaultPrice = 129000,
                            discountPrice = 0
                        ),
                        StayItem(
                            label = "호텔.리조트",
                            name = "브릿지호텔 인천송도 (구 호텔 스카이파크 인천 송도)",
                            star = "9.1",
                            commentCount = 1118,
                            location = "연수구.인천대입구역",
                            discountPer = 60,
                            defaultPrice = 250000,
                            discountPrice = 99000
                        ),
                    ),
                    isMore = true
                ),
                StayData(
                    type = Const.HOT_HOTEL,
                    "오늘 HOT 인기 펜션",
                    listOf(
                        StayItem(
                            label = "",
                            name = "태안 팜비치펜션",
                            star = "9.7",
                            commentCount = 308,
                            location = "청포대해변 앞",
                            discountPer = 0,
                            defaultPrice = 130000,
                            discountPrice = 1250000
                        ),
                        StayItem(
                            label = "",
                            name = "태안 린더버그풀빌라",
                            star = "8.7",
                            commentCount = 124,
                            location = "태안빛축제 차량 2분",
                            discountPer = 6,
                            defaultPrice = 71000,
                            discountPrice = 66740
                        ),
                        StayItem(
                            label = "",
                            name = "청도 더포레 풀빌라",
                            star = "9.3",
                            commentCount = 166,
                            location = "청도프로방스 차량 17분",
                            discountPer = 11,
                            defaultPrice = 179000,
                            discountPrice = 159847
                        ),
                    ),
                    isMore = true
                )
            )
        )
        homeData.value = testHomeData
        allCategoryList.clear()
        testHomeData.categoryList?.map {
            it.categoryList?.map { item ->
                allCategoryList.add(item)
            }
        }
    }
}
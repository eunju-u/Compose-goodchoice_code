package com.example.goodchoice.data.dataSource

import com.example.common.Const
import com.example.common.R
import com.example.domain.model.BannerData
import com.example.domain.model.CategoryData
import com.example.domain.model.CategoryItem
import com.example.domain.model.HomeData
import com.example.domain.model.OverSeaCityItem
import com.example.domain.model.OverseaSpecialItem
import com.example.domain.model.StayData
import com.example.goodchoice.data.dto.*
import javax.inject.Inject

//!! @Inject 있어야 Module 에 주입됨.
class HomeDataSource @Inject constructor() {
    fun getHomeData() =
        HomeData(
            categoryList = listOf(
                CategoryData(
                    countryType = Const.KOREA, categoryList = listOf(
                        CategoryItem(
                            id = "c_1_1",
                            name = "프리미엄블랙",
                            icon = R.drawable.img_premium,
                            code = Const.C_PREMIUM_BLACK
                        ),
                        CategoryItem(
                            id = "c_1_2",
                            name = "모텔",
                            icon = R.drawable.img_bed,
                            code = Const.C_MOTEL
                        ),
                        CategoryItem(
                            id = "c_1_3",
                            name = "호텔*리조트",
                            icon = R.drawable.img_hotel,
                            code = Const.C_HOTEL_AND_RESORT
                        ),
                        CategoryItem(
                            id = "c_1_4",
                            name = "펜션*풀빌라",
                            icon = R.drawable.img_pull,
                            code = Const.C_PENSION_AND_PULL_VILLA
                        ),
                        CategoryItem(
                            id = "c_1_5",
                            name = "홈&빌라",
                            icon = R.drawable.img_house,
                            code = Const.C_HOUSE_AND_VILLA
                        ),
                        CategoryItem(
                            id = "c_1_6",
                            name = "캠핑*글램핑",
                            R.drawable.img_camping,
                            code = Const.C_CAMPING_AND_GLAMPING
                        ),
                        CategoryItem(
                            id = "c_1_7",
                            name = "게하*한옥",
                            icon = R.drawable.img_guest_house,
                            code = Const.C_GUESTHOUSE
                        ),
                        CategoryItem(
                            id = "c_1_8",
                            name = "공간대여",
                            icon = R.drawable.img_hotel_inside,
                            code = Const.C_SPACE_RENTAL
                        ),
                        CategoryItem(
                            id = "c_1_9",
                            name = "국내 항공",
                            icon = R.drawable.img_airplane,
                            code = Const.C_KOREA_AIRPLANE
                        ),
                        CategoryItem(
                            id = "c_1_10",
                            name = "렌터카",
                            icon = R.drawable.img_car,
                            code = Const.C_RENTAL_CAR
                        ),
                        CategoryItem(
                            id = "c_1_11",
                            name = "레저*티켓",
                            icon = R.drawable.img_cablecar,
                            code = Const.C_LEISURE_AND_TICKET
                        ),
                        CategoryItem(
                            id = "c_1_12",
                            name = "맛집",
                            icon = R.drawable.img_food,
                            code = Const.C_GOOD_FOOD
                        )
                    )
                ), CategoryData(
                    countryType = Const.OVERSEA, categoryList = listOf(
                        CategoryItem(
                            id = "c_2_1",
                            name = "해외 항공",
                            icon = R.drawable.img_oversea_airplane,
                            code = Const.C_OVERSEA_AIRPLANE
                        ),
                        CategoryItem(
                            id = "c_2_2",
                            name = "해외 숙소",
                            icon = R.drawable.img_oversea_house,
                            code = Const.C_OVERSEA_STAY
                        ),
                        CategoryItem(
                            id = "c_2_3",
                            name = "항공+숙소",
                            icon = R.drawable.img_oversea,
                            code = Const.C_OVERSEA_AIRPLANE_AND_STAY
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
                    stayList = listOf(S_1, S_2, S_3, S_4, S_5, S_6, S_7),
                ),
                StayData(
                    type = Const.HOT_HOTEL, title = "이번 주 HOT 인기 펜션", stayList = listOf(
                        S_8, S_9, S_10, S_11
                    )
                )
            ), overSeaCityList = listOf(
                OverSeaCityItem(
                    id = "o_1_1",
                    cityName = "오사카",
                    code = Const.OSAKA
                ),
                OverSeaCityItem(
                    id = "o_1_2",
                    cityName = "후쿠오카",
                    code = Const.FUKUOKA
                ),
                OverSeaCityItem(
                    id = "o_1_3",
                    cityName = "도쿄",
                    code = Const.TOKYO
                ),
                OverSeaCityItem(
                    id = "o_1_4",
                    cityName = "다낭",
                    code = Const.DANANG
                ),
                OverSeaCityItem(
                    id = "o_1_5",
                    cityName = "교토",
                    code = Const.KYOTO
                ),
                OverSeaCityItem(
                    id = "o_1_6",
                    cityName = "싱가포르",
                    code = Const.SINGAPORE
                )
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
}
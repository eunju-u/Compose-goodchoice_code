package com.example.goodchoice.data.dataSource

import com.example.goodchoice.Const
import com.example.goodchoice.R
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
                OverSeaCityItem(id = "o_1_1", cityName = "오사카", code = Const.OSAKA),
                OverSeaCityItem(id = "o_1_2", cityName = "후쿠오카", code = Const.FUKUOKA),
                OverSeaCityItem(id = "o_1_3", cityName = "도쿄", code = Const.TOKYO),
                OverSeaCityItem(id = "o_1_4", cityName = "다낭", code = Const.DANANG),
                OverSeaCityItem(id = "o_1_5", cityName = "교토", code = Const.KYOTO),
                OverSeaCityItem(id = "o_1_6", cityName = "싱가포르", code = Const.SINGAPORE)
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
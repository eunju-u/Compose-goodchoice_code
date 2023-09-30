package com.example.goodchoice.ui.stayDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodchoice.Const
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StayDetailViewModel : ViewModel() {
    //서버 찌를 경우 보낼 request 파라미터 값
    var stayItemId = ""

    //서버 찌를 경우 보낼 설정한 날짜 파라미터 값
    val settingDate = ""

    //intent 통해 받아오는 숙소 이름, TopAppBarWidget 에 노출하기 위함.
    //해당 viewModel 에서 가져오지 않는 이유 : 에러시 title 노출하려고 하는데
    //서버 조회 완료와 에러시 TopAppBar 가 다르기 때문에 두개를 만들어야 해서 하나로 통일하기 위함
    var stayItemTitle = ""

    var payList : List<PayData> = emptyList()

    var detailUiState = MutableStateFlow<ConnectInfo>(ConnectInfo.Init)

    fun requestStayDetail() = viewModelScope.launch {
        detailUiState.value = ConnectInfo.Loading
        withContext(coroutineContext) {
            delay(200)
        }
        var data = StayDetailData()
        if (stayItemId == "s_1") {
            data = StayDetailData(
                label = "호텔.리조트",
                name = "양양 더 앤 리조트 호텔&스파",
                star = "8.7",
                commentCount = 906,
                location = "강원 양양군 현남면 전포매리 47-4",
                level = "4성급",
                coupon = "6% + 3000원",
                imageList = listOf("https://image.goodchoice.kr/resize_1080x500/affiliate/2020/12/16/5fd9ac35a90ce.jpg"),
                payList = listOf(
                    PayData(
                        payType = Const.PAY_TOSS,
                        payName = "토스페이",
                        payInfoList = listOf(
                            PayItem(
                                payLineTest = "최대 1만원 할인",
                                payInfo = "3만원 이상, 10% 최대 1만원 할인(오전 10시, 일450명)"
                            ),
                            PayItem(
                                payLineTest = "2천원 할인",
                                payInfo = "2만원 이상, 2천원 할인 (오후 4시, 일 1,500명)"
                            ),
                            PayItem(
                                payLineTest = "5천원 캐시백",
                                payInfo = "+생애 첫결제 시, 5천원 캐시백"
                            )
                        )
                    ),
                    PayData(
                        payType = Const.PAY_KAKAO,
                        payName = "카카오페이",
                        payInfoList = listOf(
                            PayItem(
                                payLineTest = "",
                                payInfo = "호텔/펜션 - 7만원 이상, 10% 할인 (일 900명)"
                            ),
                            PayItem(
                                payLineTest = "최대 할인금액 1만원",
                                payInfo = "최대 할인금액 1만원"
                            ),
                            PayItem(
                                payLineTest = "2천원 할인",
                                payInfo = "모텔 - 2만원 이상, 2천원 할인(일 900명)"
                            )
                        )
                    ),
                    PayData(
                        payType = Const.PAY_KB,
                        payName = "KB 페이",
                        payInfoList = listOf(
                            PayItem(
                                payLineTest = "3천원 즉시할인",
                                payInfo = "국내숙소 - 5만원 이상, 3천원 즉시할인(일 580명)"
                            )
                        )
                    ),
                    PayData(
                        payType = Const.PAY_PAYCO,
                        payName = "페이코",
                        payInfoList = listOf(
                            PayItem(
                                payLineTest = "",
                                payInfo = "국내숙소 - 3만원 이상, 5% 할인 (일 260명)"
                            ),
                            PayItem(
                                payLineTest = "최대 할인금액 4천원",
                                payInfo = "최대 할인금액 4천원"
                            )
                        )
                    )
                ),
                roomList = listOf(
                    RoomItem(
                        name = "[단독!특가] 디럭스 더블",
                        info = "기준2인 * 최대 4인 (음료)",
                        addInfo = "",
                        discountPer = 6,
                        defaultPrice = "229000",
                        discountPrice = "215260",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/12/16/5fd9ac35a90ce.jpg"
                    ), RoomItem(
                        name = "[단독특가] 디럭스 더블",
                        info = "기준2인 * 최대 4인 (음료)",
                        addInfo = "최대인원 초과 불가능",
                        discountPer = 27,
                        defaultPrice = "330000",
                        discountPrice = "242520",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/12/16/5fd9ac35a90ce.jpg"
                    ), RoomItem(
                        name = "디럭스 온돌",
                        info = "기준2인 * 최대5인",
                        addInfo = "한실침구 2채 / 최대인원 초과 불가능 / 침구추가현장결재",
                        discountPer = 27,
                        defaultPrice = "330000",
                        discountPrice = "239520",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/08/04/5f291ada2d794.jpg"
                    ), RoomItem(
                        name = "[단독!특가] 디럭스 패밀리 트윈",
                        info = "기준4인 * 최대4인",
                        discountPer = 27,
                        defaultPrice = "259000",
                        discountPrice = "240460",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/12/16/5fd9ad0de438f.jpg"
                    ), RoomItem(
                        name = "디럭스 패밀리 트윈",
                        info = "기준4인 * 최대4인",
                        addInfo = "최대인원 초과 불가능",
                        discountPer = 27,
                        defaultPrice = "370000",
                        discountPrice = "270540",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/12/16/5fd9ad0de438f.jpg"
                    ), RoomItem(
                        name = "[더 앤 키즈룸] 디럭스 더블",
                        info = "기준3인 * 최대3인",
                        discountPer = 7,
                        defaultPrice = "308000",
                        discountPrice = "286520",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2022/10/21/63524f20be80b.jpg"
                    ), RoomItem(
                        name = "[더 앤 힐링룸] 디럭스 더블",
                        info = "기준2인 * 최대2인",
                        discountPer = 7,
                        defaultPrice = "308000",
                        discountPrice = "286520",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        count = 2,
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2022/10/21/63524f4c72b38.jpg"
                    ), RoomItem(
                        name = "프리미어 스위트",
                        info = "기준4인 * 최대6인(유로)",
                        discountPer = 5,
                        defaultPrice = "532000",
                        discountPrice = "504000",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        count = 2,
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/08/04/5f291b9a2e74d.jpg"
                    ), RoomItem(
                        name = "패밀리 스위트 온돌",
                        info = "기준4인 * 최대6인",
                        addInfo = "한실침구 4채 / 최대인원 초과 불가능 / 침구추가현장결재",
                        discountPer = 50,
                        defaultPrice = "1100000",
                        discountPrice = "554000",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        count = 2,
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/08/04/5f291c5796d7d.jpg"
                    ), RoomItem(
                        name = "패밀리 스위트",
                        info = "기준4인 * 최대6인",
                        addInfo = "배드 : 퀸2 / 최대인원 초과 불가능",
                        discountPer = 50,
                        defaultPrice = "1100000",
                        discountPrice = "554000",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        count = 2,
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/08/04/5f291b9a2e74d.jpg"
                    ), RoomItem(
                        name = "더 앤 스위트",
                        info = "기준4인 * 최대6인 (유로)",
                        defaultPrice = "632000",
                        discountPrice = "604000",
                        inTime = "15:00",
                        outTime = "11:00",
                        coupon = "6%+3000원",
                        count = 2,
                        image = "https://image.goodchoice.kr/resize_480x500/affiliate/2020/08/04/5f291b9a2e74d.jpg"
                    )
                ),
                message = "양양 최상급 온천이 샘솟는 더 앤 리조트 & 스파는 자연에 둘러싸여 계절의 소리를 들으며 편안한 휴식을 즐길 수 있습니다\n" +
                        "또한 전 객실에 천연 온천수 공급 및 모든 시설이 자연 친화적이며 최첨단 건강 소재와 세련된 인테리어로 안락한 휴식이 가능한 호텔입니다",
                service = arrayListOf(
                    ServiceData(type = Const.MINIBAR, name = "미니바"),
                    ServiceData(type = Const.WIFI, name = "와이파이"),
                    ServiceData(type = Const.BATHROOM_SUPPLIES, name = "욕실용품"),
                    ServiceData(type = Const.RESTAURANT, name = "레스토랑"),
                    ServiceData(type = Const.TV, name = "TV"),
                    ServiceData(type = Const.NO_SMOKING, name = "금연"),
                    ServiceData(type = Const.AIR_CONDITIONER, name = "에어컨"),
                    ServiceData(type = Const.REFRIGERATOR, name = "냉장고"),
                    ServiceData(type = Const.SHOWER_ROOM, name = "객실샤워실"),
                    ServiceData(type = Const.DRYER, name = "드라이기"),
                    ServiceData(type = Const.CAFE, name = "카페"),
                    ServiceData(type = Const.FREE_PARKING, name = "무료주차"),
                    ServiceData(type = Const.PARKING_LOT, name = "주차장")
                )
            )
        }
        payList = data.payList?: emptyList()
        detailUiState.value = ConnectInfo.Available(data)
    }
}
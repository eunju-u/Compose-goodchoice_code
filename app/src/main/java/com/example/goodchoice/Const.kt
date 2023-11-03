package com.example.goodchoice

object Const {
    const val HOME = "Home"
    const val SEARCH = "Search"
    const val AROUND = "Around"
    const val LIKE = "Like"
    const val MY_INFO = "MyInfo"

    //카테고리 code
    const val C_PREMIUM_BLACK = "premium_black"
    const val C_MOTEL = "motel"
    const val C_HOTEL_AND_RESORT = "hotel_and_resort"
    const val C_PENSION_AND_PULL_VILLA = "pension_and_pull_villa"
    const val C_HOUSE_AND_VILLA = "house_and_villa"
    const val C_CAMPING_AND_GLAMPING = "camping_and_glamping"
    const val C_GUESTHOUSE = "guesthouse"
    const val C_SPACE_RENTAL = "space_rental"
    const val C_KOREA_AIRPLANE = "korea_airplane"
    const val C_RENTAL_CAR = "rental_car"
    const val C_LEISURE_AND_TICKET = "leisure_and_ticket"
    const val C_GOOD_FOOD = "good_food"
    const val C_OVERSEA_AIRPLANE = "oversea_airplane"
    const val C_OVERSEA_STAY = "oversea_stay"
    const val C_OVERSEA_AIRPLANE_AND_STAY = "oversea_airplane_and_stay"

    // 나라 타입
    const val KOREA = "Korea" //국내여행
    const val OVERSEA = "Oversea" //해외여행
    const val SPACE_RENTAL = "Space_Rental" //공간대여
    const val LEISURE_AND_TICKET = "Leisure_and_Ticket" //레저, 티켓

    //홈에 노출되는 숙소 타입
    const val RECENT_HOTEL = 122 //최근 본 상품
    const val TODAY_HOTEL = 123 //오늘 체크인 호텔 특가
    const val HOT_HOTEL = 124 //이번 주 HOT 인기 펜션
    const val OVERSEA_SPECIAL = 125 //해외 항공 + 숙소 이번주 특가

    //해외 도시 code
    const val OSAKA = "osaka"
    const val FUKUOKA = "fukuoka"
    const val TOKYO = "tokyo"
    const val DANANG = "danang"
    const val KYOTO = "kyoto"
    const val SINGAPORE = "singapore"

    //로그인 방법
    const val KAKAO = "KAKAO"
    const val NAVER = "NAVER"
    const val FACEBOOK = "FACEBOOK"
    const val APPLE = "APPLE"
    const val EMAIL = "EMAIL"

    //주변 > 필터 type
    const val FILTER = "FILTER" //필터
    const val RECOMMEND = "RECOMMEND" //추천순
    const val ROOM = "ROOM" //숙소유형
    const val RESERVATION = "RESERVATION" //예약가능
    const val PRICE = "PRICE"

    const val DISTANCE = "DISTANCE" //거리순
    const val HIGH_GRADE = "HIGH_GRADE" //평점높은순
    const val HIGH_REVIEW = "HIGH_REVIEW" //리뷰많은순
    const val ROW_PRICE = "ROW_PRICE" //낮은가격순
    const val HIGH_PRICE = "HIGH_PRICE" //높은가격순
    const val MOTEL = "MOTEL"
    const val HOTEL_AND_RESORT = "HOTEL_AND_RESORT"
    const val PENSION = "PENSION"
    const val HOUSE_AND_VILLA = "HOUSE_AND_VILLA"
    const val CAMPING = "CAMPING"
    const val GUESTHOUSE = "GUESTHOUSE"
    const val LESS_5 = "LESS_5"
    const val M5_L10 = "M5_L10"
    const val M10_L15 = "M10_L15"
    const val M15_L20 = "M15_L20"
    const val M20_L25 = "M20_L25"
    const val M25_L30 = "M25_L30"
    const val MORE_30 = "MORE_30"

    //결제 혜택 방식 type
    const val PAY_TOSS = "toss"
    const val PAY_KAKAO = "kakao"
    const val PAY_KB = "kb"
    const val PAY_CARD = "card"
    const val PAY_PAYCO = "payco"

    //Intent
    const val FIRST_SPLASH = "FIRST_SPLASH"
    const val WEBVIEW_TITLE = "WEBVIEW_TITLE"
    const val WEBVIEW_URL = "WEBVIEW_URL"
    const val ITEM_ID = "ITEM_ID"
    const val ITEM_TITLE = "ITEM_TITLE"
    const val DATA = "DATA"
    const val TYPE = "TYPE"

    //검색 Tab
    const val KOREA_STAY = "국내숙소"
    const val OVERSEA_STAY = "해외숙소"

    //검색 결과 > KoreaSearch type (장소인지 역인지)
    const val PLACE = "place"
    const val STATION = "station"
    const val STAY = "stay"

}

// 숙박, 대실
enum class RoomType {
    SLEEP_ROOM, RENTAL_ROOM
}

enum class CalendarPersonType {
    GUEST_ROOM, ADULT, KID
}
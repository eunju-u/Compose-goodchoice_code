package com.example.goodchoice

object Const {
    const val HOME = "Home"
    const val SEARCH = "Search"
    const val AROUND = "Around"
    const val LIKE = "Like"
    const val MY_INFO = "MyInfo"

    //카테고리 Text
    const val C_PREMIUM_BLACK = "프리미엄블랙"
    const val C_MOTEL = "모텔"
    const val C_HOTEL_AND_RESORT = "호텔*리조트"
    const val C_PENSION_AND_PULL_VILLA = "펜션*풀빌라"
    const val C_HOUSE_AND_VILLA = "홈&빌라"
    const val C_CAMPING_AND_GLAMPING = "캠핑*글램핑"
    const val C_GUESTHOUSE = "게하*한옥"
    const val C_SPACE_RENTAL = "공간대여"
    const val C_KOREA_AIRPLANE = "국내 항공"
    const val C_RENTAL_CAR = "렌터카"
    const val C_LEISURE_AND_TICKET = "레저*티켓"
    const val C_GOOD_FOOD = "맛집"
    const val C_OVERSEA_AIRPLANE = "해외 항공"
    const val C_OVERSEA_STAY = "해외 숙소"
    const val C_OVERSEA_AIRPLANE_AND_STAY = "항공+숙소"

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

    //편의시설 및 서비스
    const val WIFI = "WIFI"
    const val FULL = "FULL" //수영장
    const val SPA = "SPA"
    const val COOKING = "COOKING"
    const val BBQ = "BBQ"
    const val COOKER = "COOKER"
    const val TV = "TV"
    const val BATHROOM = "BATHROOM" //욕실
    const val FREE_PARKING = "FREE_PARKING" //무료주차
    const val AIR_CONDITIONER = "AIR_CONDITIONER" //에어컨
    const val REFRIGERATOR = "REFRIGERATOR" //냉장고
    const val SHOWER_ROOM = "SHOWER_ROOM" //샤워룸
    const val NO_SMOKING = "NO_SMOKING" //금연
    const val ANIMAL = "ANIMAL" //동물
    const val DRYER = "DRYER" //드라이기
    const val CARD = "CARD" //카드
    const val PARKING_LOT = "PARKING_LOT" //주차장
    const val COOKING_POSSIBLE = "COOKING_POSSIBLE" //취사가능
    const val MICROWAVE = "MICROWAVE" //전자레인지
    const val MINIBAR = "MINIBAR" //미니바
    const val RESTAURANT = "RESTAURANT" //레스토랑
    const val CAFE = "CAFE" //카페
    const val BATHROOM_SUPPLIES = "BATHROOM_SUPPLIES" //욕실용품

    //해외 도시
    const val OSAKA = 11000
    const val FUKUOKA = 11001
    const val TOKYO = 11002
    const val DANANG = 11003
    const val KYOTO = 11004
    const val SINGAPORE = 11005

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
}

// 숙박, 대실
enum class RoomType {
    SLEEP_ROOM, RENTAL_ROOM
}
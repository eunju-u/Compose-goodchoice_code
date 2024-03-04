package com.example.goodchoice.data.dto

import com.example.goodchoice.ServerConst

val ALL_FAVOR = FilterData(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListData(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItem(filterType = ServerConst.FAMILY_ROOM, filterTitle = "#가족여행숙소"),
                FilterItem(filterType = ServerConst.SPA, filterTitle = "#스파"),
                FilterItem(filterType = ServerConst.PARTY_ROOM, filterTitle = "#파티룸"),
                FilterItem(filterType = ServerConst.OTT, filterTitle = "#OTT"),
                FilterItem(filterType = ServerConst.FOR_COUPLE, filterTitle = "#연인추천"),
                FilterItem(filterType = ServerConst.EMOTIONAL_STAY, filterTitle = "#감성숙소"),
                FilterItem(filterType = ServerConst.GOOD_VIEW_STAY, filterTitle = "#뷰맛집"),
                FilterItem(filterType = ServerConst.CONSECUTIVE_SPECIAL, filterTitle = "#연박특가"),
                FilterItem(filterType = ServerConst.GOOD_REVIEW, filterTitle = "#리뷰좋은"),
                FilterItem(filterType = ServerConst.PET, filterTitle = "#반려견"),
                FilterItem(filterType = ServerConst.BBQ, filterTitle = "#BBQ"),
                FilterItem(filterType = ServerConst.WARM_POOL, filterTitle = "#온수풀"),
                FilterItem(filterType = ServerConst.SKI, filterTitle = "#스키장근처"),
                FilterItem(filterType = ServerConst.SUNRISE, filterTitle = "#해돋이명소")
            )
        )
    )
)

val ALL_DISCOUNT_BENEFITS = FilterData(
    code = ServerConst.DISCOUNT_BENEFITS,
    title = "할인혜택",
    list = listOf(
        FilterListData(
            code = ServerConst.DISCOUNT_BENEFITS,
            title = "할인혜택",
            list = listOf(
                FilterItem(filterType = ServerConst.COUPON_DISCOUNT, filterTitle = "쿠폰할인"),
                FilterItem(filterType = ServerConst.PAYBACK_50, filterTitle = "50%페이백"),
                FilterItem(filterType = ServerConst.DISCOUNT_SPECIAL, filterTitle = "할인특가")
            )
        )
    )
)

val ALL_RANK = FilterData(
    code = ServerConst.RANK,
    title = "등급",
    list = listOf(
        FilterListData(
            code = ServerConst.RANK,
            title = "등급",
            list = listOf(
                FilterItem(filterType = ServerConst.STAR_5, filterTitle = "5성급"),
                FilterItem(filterType = ServerConst.STAR_4, filterTitle = "4성급"),
                FilterItem(filterType = ServerConst.BLACK, filterTitle = "블랙"),
                FilterItem(filterType = ServerConst.FULL_VILLA, filterTitle = "풀빌라")
            )
        )
    )
)
val ALL_PRICE = FilterData(
    code = ServerConst.PRICE,
    title = "가격",
    list = listOf(
        FilterListData(
            code = ServerConst.PRICE,
            title = "가격",
            list = listOf(
                FilterItem(filterType = ServerConst.LESS_5, filterTitle = "~5만원"),
                FilterItem(filterType = ServerConst.M5_L10, filterTitle = "5~10만원"),
                FilterItem(filterType = ServerConst.M10_L15, filterTitle = "10~15만원"),
                FilterItem(filterType = ServerConst.M15_L20, filterTitle = "15~20만원"),
                FilterItem(filterType = ServerConst.M20_L25, filterTitle = "20~25만원"),
                FilterItem(filterType = ServerConst.M25_L30, filterTitle = "25~30만원"),
                FilterItem(filterType = ServerConst.MORE_30, filterTitle = "30만원 이상~"),
            )
        )
    )
)

val ALL_FACILITIES = FilterData(
    code = ServerConst.FACILITIES,
    title = "시설",
    list = listOf(
        FilterListData(
            code = ServerConst.PUBIC_FACILITIES,
            title = "공용시설",
            list = listOf(
                FilterItem(filterType = ServerConst.BBQ, filterTitle = "BBQ"),
                FilterItem(filterType = ServerConst.SWIMMING_POOL, filterTitle = "수영장"),
                FilterItem(filterType = ServerConst.PARKING_LOT, filterTitle = "주차장"),
                FilterItem(filterType = ServerConst.ELEVATOR, filterTitle = "엘레베이터"),
                FilterItem(filterType = ServerConst.MICROWAVE, filterTitle = "전자레인지"),
                FilterItem(filterType = ServerConst.SING_ROOM, filterTitle = "노래방"),
                FilterItem(filterType = ServerConst.SPA, filterTitle = "공용스파"),
                FilterItem(filterType = ServerConst.CAFE, filterTitle = "카페"),
                FilterItem(filterType = ServerConst.SAUNA, filterTitle = "사우나"),
                FilterItem(filterType = ServerConst.KITCHEN, filterTitle = "주방/식당"),
                FilterItem(filterType = ServerConst.PLAY_ROOM, filterTitle = "놀이방"),
                FilterItem(filterType = ServerConst.WASHING_MACHINE, filterTitle = "세탁기"),
                FilterItem(filterType = ServerConst.DRYER, filterTitle = "건조기"),
                FilterItem(filterType = ServerConst.DEHYDRATOR, filterTitle = "탈수기"),
                FilterItem(filterType = ServerConst.COOKING_POSSIBLE, filterTitle = "취사가능"),
            )
        ),
        FilterListData(
            code = ServerConst.ROOM_FACILITIES,
            title = "객실 내 시설",
            list = listOf(
                FilterItem(filterType = ServerConst.WIFI, filterTitle = "와이파이"),
                FilterItem(filterType = ServerConst.SPA, filterTitle = "객실스파"),
                FilterItem(filterType = ServerConst.AIR_CONDITIONER, filterTitle = "에어컨"),
                FilterItem(filterType = ServerConst.BATHROOM_SUPPLIES, filterTitle = "욕실용품"),
                FilterItem(filterType = ServerConst.ELECTRIC_RICE_COOKER, filterTitle = "전기밥솥"),
                FilterItem(filterType = ServerConst.TV, filterTitle = "TV"),
                FilterItem(filterType = ServerConst.MINIBAR, filterTitle = "미니바"),
                FilterItem(filterType = ServerConst.REFRIGERATOR, filterTitle = "냉장고"),
                FilterItem(filterType = ServerConst.SHOWER_ROOM, filterTitle = "객실샤워실"),
                FilterItem(filterType = ServerConst.TUB, filterTitle = "욕조"),
                FilterItem(filterType = ServerConst.DRYER, filterTitle = "드라이기"),
                FilterItem(filterType = ServerConst.WASHING_MACHINE, filterTitle = "세탁기")
            )
        ),
        FilterListData(
            code = ServerConst.OTHER_FACILITIES,
            title = "기타시설",
            list = listOf(
                FilterItem(filterType = ServerConst.PICK_UP, filterTitle = "픽업가능"),
                FilterItem(filterType = ServerConst.ACCOMPANIED_PET, filterTitle = "반려견동반"),
                FilterItem(filterType = ServerConst.IN_ROOM_COOKING, filterTitle = "객실내취사"),
                FilterItem(filterType = ServerConst.LUGGAGE_STORAGE, filterTitle = "짐보관가능"),
                FilterItem(filterType = ServerConst.FREE_PARKING, filterTitle = "무료주차"),
                FilterItem(filterType = ServerConst.BREAKFAST, filterTitle = "조식포함"),
                FilterItem(filterType = ServerConst.SMOKING, filterTitle = "객실내흡연"),
                FilterItem(filterType = ServerConst.CARD, filterTitle = "카드결제"),
                FilterItem(filterType = ServerConst.CAMPFIRE, filterTitle = "캠프파이어"),
                FilterItem(filterType = ServerConst.DISABLED_FACILITIES, filterTitle = "장애인편의시설"),
                FilterItem(filterType = ServerConst.NO_SMOKING, filterTitle = "금연")
            )
        )
    )
)

val HOUSE_AND_VILLA_FAVOR = FilterData(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListData(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItem(filterType = ServerConst.POWER_HOST, filterTitle = "#파워호스트"),
                FilterItem(filterType = ServerConst.EMOTIONAL_STAY, filterTitle = "#감성숙소"),
                FilterItem(filterType = ServerConst.COZY, filterTitle = "#아늑한"),
                FilterItem(filterType = ServerConst.MODERN, filterTitle = "#모던한"),
                FilterItem(filterType = ServerConst.RETRO, filterTitle = "#레트로"),
                FilterItem(filterType = ServerConst.FOREST, filterTitle = "#숲속"),
                FilterItem(filterType = ServerConst.STONE_HOUSE, filterTitle = "#돌담집"),
                FilterItem(filterType = ServerConst.HANOK, filterTitle = "#한옥"),
                FilterItem(filterType = ServerConst.GARDEN, filterTitle = "#정원"),
                FilterItem(filterType = ServerConst.OCEAN_VIEW, filterTitle = "#오션뷰"),
                FilterItem(filterType = ServerConst.FAMILY_TRAVEL_STAY, filterTitle = "#가족여행숙소"),
            )
        )
    )
)

val HOUSE_AND_VILLA_DISCOUNT_FACILITIES = FilterData(
    code = ServerConst.DISCOUNT_FACILITIES,
    title = "#할인 및 객실유형",
    list = listOf(
        FilterListData(
            code = ServerConst.DISCOUNT_FACILITIES,
            title = "#할인 및 객실유형",
            list = listOf(
                FilterItem(filterType = ServerConst.SUNRISE, filterTitle = "#쿠폰할인"),
                FilterItem(filterType = ServerConst.DISCOUNT_SPECIAL, filterTitle = "#할인특가"),
            )
        )
    )
)


val MOTEL_FAVOR = FilterData(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListData(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItem(filterType = ServerConst.PARTY_ROOM, filterTitle = "#파티룸"),
                FilterItem(filterType = ServerConst.OTT, filterTitle = "#OTT"),
                FilterItem(filterType = ServerConst.OCEAN_VIEW, filterTitle = "#오션뷰"),
                FilterItem(filterType = ServerConst.SUNRISE, filterTitle = "#해돋이명소"),
                FilterItem(filterType = ServerConst.STYLER, filterTitle = "#스타일러"),
                FilterItem(filterType = ServerConst.NEW_OPEN, filterTitle = "#NEW오픈"),
                FilterItem(filterType = ServerConst.SKI, filterTitle = "#스키장근처"),
            )
        )
    )
)

val MOTEL_DISCOUNT_BENEFITS = FilterData(
    code = ServerConst.DISCOUNT_BENEFITS,
    title = "할인혜택",
    list = listOf(
        FilterListData(
            code = ServerConst.DISCOUNT_BENEFITS,
            title = "할인혜택",
            list = listOf(
                FilterItem(filterType = ServerConst.COUPON_DISCOUNT, filterTitle = "쿠폰할인"),
            )
        )
    )
)

val CAMPING_FAVOR = FilterData(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListData(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItem(filterType = ServerConst.WATER_PLAY, filterTitle = "#물놀이가능"),
                FilterItem(filterType = ServerConst.BBQ, filterTitle = "#BBQ"),
                FilterItem(filterType = ServerConst.FOREST_VIEW, filterTitle = "#포레스트뷰"),
                FilterItem(filterType = ServerConst.INDIVIDUAL_BATHROOM, filterTitle = "#개별화장실"),
                FilterItem(filterType = ServerConst.PET, filterTitle = "#반려견"),
                FilterItem(filterType = ServerConst.BULMEONG, filterTitle = "#불멍"),
                FilterItem(filterType = ServerConst.EMOTIONAL_STAY, filterTitle = "#감성숙소"),
                FilterItem(filterType = ServerConst.FOR_COUPLE, filterTitle = "#연인추천"),
                FilterItem(filterType = ServerConst.FOR_FAMILY, filterTitle = "#가족추천"),
                FilterItem(filterType = ServerConst.GOOD_REVIEW, filterTitle = "#리뷰좋은"),
                FilterItem(filterType = ServerConst.CAMPING_CAR, filterTitle = "#캠핑카"),
                FilterItem(filterType = ServerConst.AUTO_CAMPING, filterTitle = "#오토캠핑"),
            )
        )
    )
)

val CAMPING_TYPE = FilterData(
    code = ServerConst.FAVOR,
    title = "#캠핑유형",
    list = listOf(
        FilterListData(
            code = ServerConst.FAVOR,
            title = "#캠핑유형",
            list = listOf(
                FilterItem(filterType = ServerConst.GLAMPING, filterTitle = "#글램핑"),
                FilterItem(filterType = ServerConst.CARAVAN, filterTitle = "#카라반"),
                FilterItem(filterType = ServerConst.AUTO_CAMPING, filterTitle = "#오토캠핑"),

                )
        )
    )
)


package com.example.filter.data.remote.mock

import com.example.common.ServerConst
import com.example.filter.data.remote.dto.FilterDto
import com.example.filter.data.remote.dto.FilterItemDto
import com.example.filter.data.remote.dto.FilterListDto

val ALL_FAVOR = FilterDto(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListDto(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.FAMILY_ROOM,
                    filterTitle = "#가족여행숙소"
                ),
                FilterItemDto(
                    filterType = ServerConst.SPA,
                    filterTitle = "#스파"
                ),
                FilterItemDto(
                    filterType = ServerConst.PARTY_ROOM,
                    filterTitle = "#파티룸"
                ),
                FilterItemDto(
                    filterType = ServerConst.OTT,
                    filterTitle = "#OTT"
                ),
                FilterItemDto(
                    filterType = ServerConst.FOR_COUPLE,
                    filterTitle = "#연인추천"
                ),
                FilterItemDto(
                    filterType = ServerConst.EMOTIONAL_STAY,
                    filterTitle = "#감성숙소"
                ),
                FilterItemDto(
                    filterType = ServerConst.GOOD_VIEW_STAY,
                    filterTitle = "#뷰맛집"
                ),
                FilterItemDto(
                    filterType = ServerConst.CONSECUTIVE_SPECIAL,
                    filterTitle = "#연박특가"
                ),
                FilterItemDto(
                    filterType = ServerConst.GOOD_REVIEW,
                    filterTitle = "#리뷰좋은"
                ),
                FilterItemDto(
                    filterType = ServerConst.PET,
                    filterTitle = "#반려견"
                ),
                FilterItemDto(
                    filterType = ServerConst.BBQ,
                    filterTitle = "#BBQ"
                ),
                FilterItemDto(
                    filterType = ServerConst.WARM_POOL,
                    filterTitle = "#온수풀"
                ),
                FilterItemDto(
                    filterType = ServerConst.SKI,
                    filterTitle = "#스키장근처"
                ),
                FilterItemDto(
                    filterType = ServerConst.SUNRISE,
                    filterTitle = "#해돋이명소"
                )
            )
        )
    )
)

val ALL_DISCOUNT_BENEFITS = FilterDto(
    code = ServerConst.DISCOUNT_BENEFITS,
    title = "할인혜택",
    list = listOf(
        FilterListDto(
            code = ServerConst.DISCOUNT_BENEFITS,
            title = "할인혜택",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.COUPON_DISCOUNT,
                    filterTitle = "쿠폰할인"
                ),
                FilterItemDto(
                    filterType = ServerConst.PAYBACK_50,
                    filterTitle = "50%페이백"
                ),
                FilterItemDto(
                    filterType = ServerConst.DISCOUNT_SPECIAL,
                    filterTitle = "할인특가"
                )
            )
        )
    )
)

val ALL_RANK = FilterDto(
    code = ServerConst.RANK,
    title = "등급",
    list = listOf(
        FilterListDto(
            code = ServerConst.RANK,
            title = "등급",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.STAR_5,
                    filterTitle = "5성급"
                ),
                FilterItemDto(
                    filterType = ServerConst.STAR_4,
                    filterTitle = "4성급"
                ),
                FilterItemDto(
                    filterType = ServerConst.BLACK,
                    filterTitle = "블랙"
                ),
                FilterItemDto(
                    filterType = ServerConst.FULL_VILLA,
                    filterTitle = "풀빌라"
                )
            )
        )
    )
)
val ALL_PRICE = FilterDto(
    code = ServerConst.PRICE,
    title = "가격",
    list = listOf(
        FilterListDto(
            code = ServerConst.PRICE,
            title = "가격",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.LESS_5,
                    filterTitle = "~5만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.M5_L10,
                    filterTitle = "5~10만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.M10_L15,
                    filterTitle = "10~15만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.M15_L20,
                    filterTitle = "15~20만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.M20_L25,
                    filterTitle = "20~25만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.M25_L30,
                    filterTitle = "25~30만원"
                ),
                FilterItemDto(
                    filterType = ServerConst.MORE_30,
                    filterTitle = "30만원 이상~"
                ),
            )
        )
    )
)

val ALL_FACILITIES = FilterDto(
    code = ServerConst.FACILITIES,
    title = "시설",
    list = listOf(
        FilterListDto(
            code = ServerConst.PUBIC_FACILITIES,
            title = "공용시설",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.BBQ,
                    filterTitle = "BBQ"
                ),
                FilterItemDto(
                    filterType = ServerConst.SWIMMING_POOL,
                    filterTitle = "수영장"
                ),
                FilterItemDto(
                    filterType = ServerConst.PARKING_LOT,
                    filterTitle = "주차장"
                ),
                FilterItemDto(
                    filterType = ServerConst.ELEVATOR,
                    filterTitle = "엘레베이터"
                ),
                FilterItemDto(
                    filterType = ServerConst.MICROWAVE,
                    filterTitle = "전자레인지"
                ),
                FilterItemDto(
                    filterType = ServerConst.SING_ROOM,
                    filterTitle = "노래방"
                ),
                FilterItemDto(
                    filterType = ServerConst.SPA,
                    filterTitle = "공용스파"
                ),
                FilterItemDto(
                    filterType = ServerConst.CAFE,
                    filterTitle = "카페"
                ),
                FilterItemDto(
                    filterType = ServerConst.SAUNA,
                    filterTitle = "사우나"
                ),
                FilterItemDto(
                    filterType = ServerConst.KITCHEN,
                    filterTitle = "주방/식당"
                ),
                FilterItemDto(
                    filterType = ServerConst.PLAY_ROOM,
                    filterTitle = "놀이방"
                ),
                FilterItemDto(
                    filterType = ServerConst.WASHING_MACHINE,
                    filterTitle = "세탁기"
                ),
                FilterItemDto(
                    filterType = ServerConst.DRYER,
                    filterTitle = "건조기"
                ),
                FilterItemDto(
                    filterType = ServerConst.DEHYDRATOR,
                    filterTitle = "탈수기"
                ),
                FilterItemDto(
                    filterType = ServerConst.COOKING_POSSIBLE,
                    filterTitle = "취사가능"
                ),
            )
        ),
        FilterListDto(
            code = ServerConst.ROOM_FACILITIES,
            title = "객실 내 시설",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.WIFI,
                    filterTitle = "와이파이"
                ),
                FilterItemDto(
                    filterType = ServerConst.SPA,
                    filterTitle = "객실스파"
                ),
                FilterItemDto(
                    filterType = ServerConst.AIR_CONDITIONER,
                    filterTitle = "에어컨"
                ),
                FilterItemDto(
                    filterType = ServerConst.BATHROOM_SUPPLIES,
                    filterTitle = "욕실용품"
                ),
                FilterItemDto(
                    filterType = ServerConst.ELECTRIC_RICE_COOKER,
                    filterTitle = "전기밥솥"
                ),
                FilterItemDto(
                    filterType = ServerConst.TV,
                    filterTitle = "TV"
                ),
                FilterItemDto(
                    filterType = ServerConst.MINIBAR,
                    filterTitle = "미니바"
                ),
                FilterItemDto(
                    filterType = ServerConst.REFRIGERATOR,
                    filterTitle = "냉장고"
                ),
                FilterItemDto(
                    filterType = ServerConst.SHOWER_ROOM,
                    filterTitle = "객실샤워실"
                ),
                FilterItemDto(
                    filterType = ServerConst.TUB,
                    filterTitle = "욕조"
                ),
                FilterItemDto(
                    filterType = ServerConst.DRYER,
                    filterTitle = "드라이기"
                ),
                FilterItemDto(
                    filterType = ServerConst.WASHING_MACHINE,
                    filterTitle = "세탁기"
                )
            )
        ),
        FilterListDto(
            code = ServerConst.OTHER_FACILITIES,
            title = "기타시설",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.PICK_UP,
                    filterTitle = "픽업가능"
                ),
                FilterItemDto(
                    filterType = ServerConst.ACCOMPANIED_PET,
                    filterTitle = "반려견동반"
                ),
                FilterItemDto(
                    filterType = ServerConst.IN_ROOM_COOKING,
                    filterTitle = "객실내취사"
                ),
                FilterItemDto(
                    filterType = ServerConst.LUGGAGE_STORAGE,
                    filterTitle = "짐보관가능"
                ),
                FilterItemDto(
                    filterType = ServerConst.FREE_PARKING,
                    filterTitle = "무료주차"
                ),
                FilterItemDto(
                    filterType = ServerConst.BREAKFAST,
                    filterTitle = "조식포함"
                ),
                FilterItemDto(
                    filterType = ServerConst.SMOKING,
                    filterTitle = "객실내흡연"
                ),
                FilterItemDto(
                    filterType = ServerConst.CARD,
                    filterTitle = "카드결제"
                ),
                FilterItemDto(
                    filterType = ServerConst.CAMPFIRE,
                    filterTitle = "캠프파이어"
                ),
                FilterItemDto(
                    filterType = ServerConst.DISABLED_FACILITIES,
                    filterTitle = "장애인편의시설"
                ),
                FilterItemDto(
                    filterType = ServerConst.NO_SMOKING,
                    filterTitle = "금연"
                )
            )
        )
    )
)

val HOUSE_AND_VILLA_FAVOR = FilterDto(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListDto(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.POWER_HOST,
                    filterTitle = "#파워호스트"
                ),
                FilterItemDto(
                    filterType = ServerConst.EMOTIONAL_STAY,
                    filterTitle = "#감성숙소"
                ),
                FilterItemDto(
                    filterType = ServerConst.COZY,
                    filterTitle = "#아늑한"
                ),
                FilterItemDto(
                    filterType = ServerConst.MODERN,
                    filterTitle = "#모던한"
                ),
                FilterItemDto(
                    filterType = ServerConst.RETRO,
                    filterTitle = "#레트로"
                ),
                FilterItemDto(
                    filterType = ServerConst.FOREST,
                    filterTitle = "#숲속"
                ),
                FilterItemDto(
                    filterType = ServerConst.STONE_HOUSE,
                    filterTitle = "#돌담집"
                ),
                FilterItemDto(
                    filterType = ServerConst.HANOK,
                    filterTitle = "#한옥"
                ),
                FilterItemDto(
                    filterType = ServerConst.GARDEN,
                    filterTitle = "#정원"
                ),
                FilterItemDto(
                    filterType = ServerConst.OCEAN_VIEW,
                    filterTitle = "#오션뷰"
                ),
                FilterItemDto(
                    filterType = ServerConst.FAMILY_TRAVEL_STAY,
                    filterTitle = "#가족여행숙소"
                ),
            )
        )
    )
)

val HOUSE_AND_VILLA_DISCOUNT_FACILITIES = FilterDto(
    code = ServerConst.DISCOUNT_FACILITIES,
    title = "#할인 및 객실유형",
    list = listOf(
        FilterListDto(
            code = ServerConst.DISCOUNT_FACILITIES,
            title = "#할인 및 객실유형",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.SUNRISE,
                    filterTitle = "#쿠폰할인"
                ),
                FilterItemDto(
                    filterType = ServerConst.DISCOUNT_SPECIAL,
                    filterTitle = "#할인특가"
                ),
            )
        )
    )
)


val MOTEL_FAVOR = FilterDto(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListDto(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.PARTY_ROOM,
                    filterTitle = "#파티룸"
                ),
                FilterItemDto(
                    filterType = ServerConst.OTT,
                    filterTitle = "#OTT"
                ),
                FilterItemDto(
                    filterType = ServerConst.OCEAN_VIEW,
                    filterTitle = "#오션뷰"
                ),
                FilterItemDto(
                    filterType = ServerConst.SUNRISE,
                    filterTitle = "#해돋이명소"
                ),
                FilterItemDto(
                    filterType = ServerConst.STYLER,
                    filterTitle = "#스타일러"
                ),
                FilterItemDto(
                    filterType = ServerConst.NEW_OPEN,
                    filterTitle = "#NEW오픈"
                ),
                FilterItemDto(
                    filterType = ServerConst.SKI,
                    filterTitle = "#스키장근처"
                ),
            )
        )
    )
)

val MOTEL_DISCOUNT_BENEFITS = FilterDto(
    code = ServerConst.DISCOUNT_BENEFITS,
    title = "할인혜택",
    list = listOf(
        FilterListDto(
            code = ServerConst.DISCOUNT_BENEFITS,
            title = "할인혜택",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.COUPON_DISCOUNT,
                    filterTitle = "쿠폰할인"
                ),
            )
        )
    )
)

val CAMPING_FAVOR = FilterDto(
    code = ServerConst.FAVOR,
    title = "#취향",
    list = listOf(
        FilterListDto(
            code = ServerConst.FAVOR,
            title = "#취향",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.WATER_PLAY,
                    filterTitle = "#물놀이가능"
                ),
                FilterItemDto(
                    filterType = ServerConst.BBQ,
                    filterTitle = "#BBQ"
                ),
                FilterItemDto(
                    filterType = ServerConst.FOREST_VIEW,
                    filterTitle = "#포레스트뷰"
                ),
                FilterItemDto(
                    filterType = ServerConst.INDIVIDUAL_BATHROOM,
                    filterTitle = "#개별화장실"
                ),
                FilterItemDto(
                    filterType = ServerConst.PET,
                    filterTitle = "#반려견"
                ),
                FilterItemDto(
                    filterType = ServerConst.BULMEONG,
                    filterTitle = "#불멍"
                ),
                FilterItemDto(
                    filterType = ServerConst.EMOTIONAL_STAY,
                    filterTitle = "#감성숙소"
                ),
                FilterItemDto(
                    filterType = ServerConst.FOR_COUPLE,
                    filterTitle = "#연인추천"
                ),
                FilterItemDto(
                    filterType = ServerConst.FOR_FAMILY,
                    filterTitle = "#가족추천"
                ),
                FilterItemDto(
                    filterType = ServerConst.GOOD_REVIEW,
                    filterTitle = "#리뷰좋은"
                ),
                FilterItemDto(
                    filterType = ServerConst.CAMPING_CAR,
                    filterTitle = "#캠핑카"
                ),
                FilterItemDto(
                    filterType = ServerConst.AUTO_CAMPING,
                    filterTitle = "#오토캠핑"
                ),
            )
        )
    )
)

val CAMPING_TYPE = FilterDto(
    code = ServerConst.FAVOR,
    title = "#캠핑유형",
    list = listOf(
        FilterListDto(
            code = ServerConst.FAVOR,
            title = "#캠핑유형",
            list = listOf(
                FilterItemDto(
                    filterType = ServerConst.GLAMPING,
                    filterTitle = "#글램핑"
                ),
                FilterItemDto(
                    filterType = ServerConst.CARAVAN,
                    filterTitle = "#카라반"
                ),
                FilterItemDto(
                    filterType = ServerConst.AUTO_CAMPING,
                    filterTitle = "#오토캠핑"
                ),

                )
        )
    )
)


package com.example.around.ui.intent

import com.example.common.RoomType
import com.example.domain.model.KoreaSearchData

sealed class AroundIntent {
    object LoadData : AroundIntent()
    data class SelectRoomType(val roomType: RoomType) : AroundIntent()
    data class SelectSearchItem(val searchItem: KoreaSearchData) : AroundIntent()
    object ResetFilter : AroundIntent()
    data class Retry(val reason: String) : AroundIntent()
}
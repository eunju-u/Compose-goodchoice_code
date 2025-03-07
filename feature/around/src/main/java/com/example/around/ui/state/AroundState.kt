package com.example.around.ui.state

import com.example.around.ui.model.AroundFilterSelectedData
import com.example.common.RoomType
import com.example.domain.model.KoreaSearchData

data class AroundState(
    val aroundFilterSelect: AroundFilterSelectedData = AroundFilterSelectedData(),
    val selectRoomType: RoomType = RoomType.SLEEP_ROOM,
    val selectSearchItem: KoreaSearchData = KoreaSearchData(),
    val uiState: AroundUiState = AroundUiState.Loading
)
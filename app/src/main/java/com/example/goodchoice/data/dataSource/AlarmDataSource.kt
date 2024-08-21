package com.example.goodchoice.data.dataSource

import com.example.domain.model.AlarmItem
import javax.inject.Inject

class AlarmDataSource @Inject constructor() {
    fun getAlarmData() = listOf(
        AlarmItem(
            alarmIdx = 0,
            title = "알림함이 새롭게 단장되었어요!",
            content = "이제 예약부터 할인 * 혜택 소식까지 알림함에서 모두 확인 할 수 있어요",
            date = "2023-08-26"
        )
    )
}
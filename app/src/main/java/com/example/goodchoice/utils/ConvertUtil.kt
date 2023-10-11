package com.example.goodchoice.utils

import com.example.goodchoice.Const
import com.example.goodchoice.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ConvertUtil {

    /**
     * 콤마 변환 20000 -> 20,000
     */
    fun convertCommaString(source: Any?): String {
        val sb = StringBuilder()
        source?.let {
            var str: Any
            if (it is String) {
                str = it
            } else {
                str = it.toString()
                if (str == "") {
                    return sb.toString()
                }
            }


            var isMinus = false
            // 마이너스인 경우 처리
            if (str[0] == '-') {
                str = str.replace("-".toRegex(), "")
                isMinus = true
            }
            val len = str.length
            for ((offset, i) in (len - 1 downTo 0).withIndex()) {
                if (offset != 0 && offset % 3 == 0) {
                    sb.insert(0, ",")
                }
                val c = str[i]
                sb.insert(0, c)
            }
            if (isMinus) {
                sb.insert(0, "-")
            }
        }

        return sb.toString()
    }

    fun convertOverSeaImage(cityId: Int): Int {
        return when (cityId) {
            Const.OSAKA -> R.drawable.img_osaka
            Const.FUKUOKA -> R.drawable.img_fukuoka
            Const.TOKYO -> R.drawable.img_tokyo
            Const.DANANG -> R.drawable.img_danang
            Const.KYOTO -> R.drawable.img_kyoto
            Const.SINGAPORE -> R.drawable.img_singapore
            else -> R.drawable.bg_white
        }
    }

    fun convertPayImage(payType: String): Int {
        return when (payType) {
            Const.PAY_TOSS -> R.drawable.img_toss
            Const.PAY_KAKAO -> R.drawable.bg_white
            Const.PAY_KB -> R.drawable.bg_white
            Const.PAY_PAYCO -> R.drawable.bg_white
            else -> R.drawable.bg_white
        }
    }

    fun convertServiceImage(serviceType: String): Int {
        return when (serviceType) {
            Const.MINIBAR -> R.drawable.ic_minibar
            Const.WIFI -> R.drawable.ic_wifi
            Const.BATHROOM_SUPPLIES -> R.drawable.ic_towel
            Const.RESTAURANT -> R.drawable.ic_restaurant
            Const.TV -> R.drawable.ic_tv
            Const.NO_SMOKING -> R.drawable.ic_no_smoking
            Const.AIR_CONDITIONER -> R.drawable.ic_air_conditioner
            Const.REFRIGERATOR -> R.drawable.ic_refrigerator
            Const.SHOWER_ROOM -> R.drawable.ic_shower_room
            Const.DRYER -> R.drawable.ic_dryer
            Const.CAFE -> R.drawable.ic_cafe
            Const.FREE_PARKING -> R.drawable.ic_parking
            Const.PARKING_LOT -> R.drawable.ic_parking
            else -> R.drawable.bg_white
        }
    }

    fun convertDayOfWeek(day: String): Int {
        return when (day) {
            "MONDAY" -> R.string.str_monday
            "TUESDAY" -> R.string.str_tuesday
            "WEDNESDAY" -> R.string.str_wednesday
            "THURSDAY" -> R.string.str_thursday
            "FRIDAY" -> R.string.str_friday
            "SATURDAY" -> R.string.str_saturday
            else -> R.string.str_sunday
        }
    }

    fun convertMonth(month: String): Int {
        return when (month) {
            "JANUARY" -> 1
            "FEBRUARY" -> 2
            "MARCH" -> 3
            "APRIL" -> 4
            "MAY" -> 5
            "JUNE" -> 6
            "JULY" -> 7
            "AUGUST" -> 8
            "SEPTEMBER" -> 9
            "OCTOBER" -> 10
            "NOVEMBER" -> 11
            "DECEMBER" -> 12
            else -> 1
        }
    }

    /**
     * 2023-10-05 -> 10.5 로 포멧
     */
    fun formatDate(dateString: String?): String {
        return dateString?.run {
            val date = LocalDate.parse(dateString)
            val formatter = DateTimeFormatter.ofPattern("M.d")
            date.format(formatter)
        } ?: ""
    }
}
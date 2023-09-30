package com.example.goodchoice.utils

import com.example.goodchoice.Const
import com.example.goodchoice.R

object ConvertUtil {

    fun convertOverSeaImage(cityId: Int): Int {
        val image = when (cityId) {
            Const.OSAKA -> R.drawable.img_osaka
            Const.FUKUOKA -> R.drawable.img_fukuoka
            Const.TOKYO -> R.drawable.img_tokyo
            Const.DANANG -> R.drawable.img_danang
            Const.KYOTO -> R.drawable.img_kyoto
            Const.SINGAPORE -> R.drawable.img_singapore
            else -> R.drawable.bg_white
        }
        return image
    }

    fun convertPayImage(payType: String): Int {
        val image = when (payType) {
            Const.PAY_TOSS -> R.drawable.img_toss
            Const.PAY_KAKAO -> R.drawable.bg_white
            Const.PAY_KB -> R.drawable.bg_white
            Const.PAY_PAYCO -> R.drawable.bg_white
            else -> R.drawable.bg_white
        }
        return image
    }

    fun convertServiceImage(serviceType: String): Int {
        val image = when (serviceType) {
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
        return image
    }
}
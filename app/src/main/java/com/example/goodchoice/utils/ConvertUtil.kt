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
}
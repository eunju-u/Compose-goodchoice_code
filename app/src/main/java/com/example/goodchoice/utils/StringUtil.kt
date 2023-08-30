package com.example.goodchoice.utils

object StringUtil {

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
}
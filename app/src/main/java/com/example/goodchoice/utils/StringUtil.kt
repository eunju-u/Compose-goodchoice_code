package com.example.goodchoice.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.goodchoice.ui.theme.Theme

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

    /**
     * 특정 문자열의 color 변환
     */
    @Composable
    fun setTextColor(
        originText: String,
        targetText: String,
        color: Color = Theme.colorScheme.blue
    ): AnnotatedString {
        return buildAnnotatedString {
            // for 문으로 하려고 했지만 oom 나서 수정함
            val startIndex = originText.indexOf(targetText)
            if (startIndex == -1) {
                // 특정 문자열이 없으면 originText 으로 return.
                append(originText)
            } else {
                val endIndex = startIndex + targetText.length
                if (startIndex == 0) {
                    withStyle(style = SpanStyle(color = color)) {
                        append(originText.substring(startIndex, endIndex))
                    }
                    if (endIndex != originText.length) {
                        append(originText.substring(endIndex, originText.length))
                    }
                } else {
                    append(originText.substring(0, startIndex))
                    withStyle(style = SpanStyle(color = color)) {
                        append(originText.substring(startIndex, endIndex))
                    }
                    if (endIndex != originText.length) {
                        append(originText.substring(endIndex, originText.length))
                    }
                }
            }
        }
    }

    /**
     * 특정 문자열의 font weight 변환
     */
    @Composable
    fun setTextBold(
        originText: String,
        targetText: String,
    ): AnnotatedString {
        return buildAnnotatedString {
            // for 문으로 하려고 했지만 oom 나서 수정함
            val startIndex = originText.indexOf(targetText)
            if (startIndex == -1) {
                // 특정 문자열이 없으면 originText 으로 return.
                append(originText)
            } else {
                val endIndex = startIndex + targetText.length
                if (startIndex == 0) {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(originText.substring(startIndex, endIndex))
                    }
                    if (endIndex != originText.length) {
                        append(originText.substring(endIndex, originText.length))
                    }
                } else {
                    append(originText.substring(0, startIndex))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(originText.substring(startIndex, endIndex))
                    }
                    if (endIndex != originText.length) {
                        append(originText.substring(endIndex, originText.length))
                    }
                }
            }
        }
    }
}
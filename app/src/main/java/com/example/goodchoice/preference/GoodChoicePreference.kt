package com.example.goodchoice.preference

import android.content.Context
import java.time.LocalDate

class GoodChoicePreference(context: Context) : DefaultPreference(context) {

    private val IS_LOGIN = "isLogin"
    private val LOGIN_WAY = "loginWay"

    private val START_DATE = "startDate"
    private val END_DATE = "endDate"
    private val PERSON_COUNT = "personCount"

    //로그인 여부색
    var isLogin: Boolean
        set(value) {
            setPreference(IS_LOGIN, value)
        }
        get() {
            return getPreferenceBoolean(IS_LOGIN, false)
        }

    //로그인(카카오, 네이버, 페이스북, 애플, 이메일) 어떤 방법으로 시도했는지 확인
    var loginWay: String
        set(value) {
            setPreference(LOGIN_WAY, value)
        }
        get() {
            return getPreferenceString(LOGIN_WAY, "KAKAO") ?: "KAKAO"
        }

    var startDate: String?
        set(value) {
            value?.let {
                setPreference(START_DATE, value)
            }
        }
        get() {
            return getPreferenceString(START_DATE, LocalDate.now().toString())
                ?: LocalDate.now().toString()
        }

    var endDate: String?
        set(value) {
            value?.let {
                setPreference(END_DATE, value)
            }
        }
        get() {
            return getPreferenceString(END_DATE, LocalDate.parse(startDate).plusDays(1).toString())
                ?: LocalDate.parse(startDate).plusDays(1).toString()
        }

    var personCount: Int
        set(value) {
            setPreference(PERSON_COUNT, value)
        }
        get() {
            return getPreferenceInt(PERSON_COUNT, 2)
        }
}
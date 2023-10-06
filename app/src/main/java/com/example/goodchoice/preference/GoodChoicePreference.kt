package com.example.goodchoice.preference

import android.content.Context
import java.time.LocalDate

class GoodChoicePreference(context: Context) : DefaultPreference(context) {

    private val IS_LOGIN = "isLogin"
    private val LOGIN_WAY = "loginWay"

    private val START_DATE = "startDate"
    private val END_DATE = "endDate"
    private val PERSON_COUNT = "personCount"

    //로그인 여부
    fun setIsLogin(value: Boolean) {
        setPreference(IS_LOGIN, value)
    }

    fun isLogin(): Boolean {
        return getPreferenceBoolean(IS_LOGIN, false)
    }

    //로그인(카카오, 네이버, 페이스북, 애플, 이메일) 어떤 방법으로 시도했는지 확인
    fun setLoginWay(value: String) {
        setPreference(LOGIN_WAY, value)
    }

    fun getLoginWay(): String {
        return getPreferenceString(LOGIN_WAY, "KAKAO") ?: "KAKAO"
    }

    fun setStartDate(startDate: String) {
        setPreference(START_DATE, startDate)
    }

    fun getStartDate(): String {
        return getPreferenceString(START_DATE, LocalDate.now().toString())
            ?: LocalDate.now().toString()
    }

    fun setEndDate(endDate: String) {
        setPreference(END_DATE, endDate)
    }

    fun getEndDate(): String {
        return getPreferenceString(END_DATE, LocalDate.now().plusDays(1).toString())
            ?: LocalDate.now().plusDays(1).toString()
    }

    fun setPersonCount(person: Int) {
        setPreference(PERSON_COUNT, person)
    }

    fun getPersonCount(): Int {
        return getPreferenceInt(PERSON_COUNT, 2)
    }

}
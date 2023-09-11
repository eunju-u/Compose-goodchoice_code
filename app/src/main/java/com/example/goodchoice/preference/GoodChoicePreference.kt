package com.example.goodchoice.preference

import android.content.Context

class GoodChoicePreference(context: Context) : DefaultPreference(context) {

    private val IS_LOGIN = "isLogin"
    private val LOGIN_WAY = "loginWay"

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
}
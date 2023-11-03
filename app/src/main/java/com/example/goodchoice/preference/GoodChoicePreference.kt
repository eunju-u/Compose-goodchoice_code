package com.example.goodchoice.preference

import android.content.Context
import java.time.LocalDate

class GoodChoicePreference(context: Context) : DefaultPreference(context) {

    private val IS_LOGIN = "isLogin"
    private val LOGIN_WAY = "loginWay"

    private val KOREA_START_DATE = "koreaStartDate"   //국내 여행 시작 날짜
    private val KOREA_END_DATE = "koreaEndDate"    //국내 여행 마지막 날짜
    private val OVERSEA_START_DATE = "overseaStartDate"   //해외 여행 시작 날짜
    private val OVERSEA_END_DATE = "overseaEndDate"    //해외 여행 마지막 날짜
    private val KOREA_PERSON_COUNT = "koreaPersonCount"   //국내 여행 인원수
    private val OVERSEA_STAY_COUNT = "overseaStayCount"   //해외 여행 숙소갯수
    private val OVERSEA_ADULT_COUNT = "overseaAdultCount"   //해외 여행 성인수
    private val OVERSEA_KID_COUNT = "overseaKidCount"   //해외 여행 아동수


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

    var koreaStartDate: String?
        set(value) {
            value?.let {
                setPreference(KOREA_START_DATE, value)
            }
        }
        get() {
            return getPreferenceString(KOREA_START_DATE, LocalDate.now().toString())
                ?: LocalDate.now().toString()
        }

    var koreaEndDate: String?
        set(value) {
            value?.let {
                setPreference(KOREA_END_DATE, value)
            }
        }
        get() {
            return getPreferenceString(KOREA_END_DATE, LocalDate.parse(koreaStartDate).plusDays(1).toString())
                ?: LocalDate.parse(koreaStartDate).plusDays(1).toString()
        }


    var overseaStartDate: String?
        set(value) {
            value?.let {
                setPreference(OVERSEA_START_DATE, value)
            }
        }
        get() {
            return getPreferenceString(OVERSEA_START_DATE, LocalDate.now().toString())
                ?: LocalDate.now().toString()
        }

    var overseaEndDate: String?
        set(value) {
            value?.let {
                setPreference(OVERSEA_END_DATE, value)
            }
        }
        get() {
            return getPreferenceString(OVERSEA_END_DATE, LocalDate.parse(overseaStartDate).plusDays(1).toString())
                ?: LocalDate.parse(overseaStartDate).plusDays(1).toString()
        }

    var koreaPersonCount: Int
        set(value) {
            setPreference(KOREA_PERSON_COUNT, value)
        }
        get() {
            return getPreferenceInt(KOREA_PERSON_COUNT, 2)
        }

    var overseaStayCount: Int
        set(value) {
            setPreference(OVERSEA_STAY_COUNT, value)
        }
        get() {
            return getPreferenceInt(OVERSEA_STAY_COUNT, 1)
        }

    var overseaAdultCount: Int
        set(value) {
            setPreference(OVERSEA_ADULT_COUNT, value)
        }
        get() {
            return getPreferenceInt(OVERSEA_ADULT_COUNT, 2)
        }

    var overseaKidCount: Int
        set(value) {
            setPreference(OVERSEA_KID_COUNT, value)
        }
        get() {
            return getPreferenceInt(OVERSEA_KID_COUNT, 0)
        }
}
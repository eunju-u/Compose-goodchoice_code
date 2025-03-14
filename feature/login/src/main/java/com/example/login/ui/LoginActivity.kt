package com.example.login.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui_theme.TestTheme
import com.example.ui_common.utils.StringUtil

class LoginActivity : ComponentActivity() {
    companion object {
        val TAG: String = LoginActivity::class.java.simpleName
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = GoodChoicePreference(this)

        setContent {
            TestTheme {
                LoginContent(isLoginWay = pref.loginWay,
                    onFinish = {
                        if (it.isNotEmpty()) {
                            pref.isLogin = true
                            // 로그인 방법이 변경 됐을 경우만 userName 변경
                            if (pref.loginWay != it) {
                                pref.userName = StringUtil.randomUserName()
                            }
                            pref.loginWay = it
                        }
                        this.finish()
                    })
            }
        }
    }
}
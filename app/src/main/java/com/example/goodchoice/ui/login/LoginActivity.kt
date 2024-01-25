package com.example.goodchoice.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.theme.TestTheme
import com.example.goodchoice.utils.StringUtil

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
                            pref.loginWay = it
                            pref.userName = StringUtil.randomUserName()
                        }
                        this.finish()
                    })
            }
        }
    }
}
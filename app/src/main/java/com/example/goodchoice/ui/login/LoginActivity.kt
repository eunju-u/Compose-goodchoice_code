package com.example.goodchoice.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.goodchoice.ui.theme.TestTheme

class LoginActivity : ComponentActivity() {
    companion object {
        val TAG: String = LoginActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                LoginContent(onFinish = { this.finish() })
            }
        }
    }
}
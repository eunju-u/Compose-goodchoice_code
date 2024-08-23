package com.example.goodchoice.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.common.Const
import com.example.goodchoice.ui.main.MainActivity
import com.example.ui.theme.TestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    companion object {
        val TAG: String = SplashActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                SplashContent()
            }
        }

        //permission 설정
        requestPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        //뷰 생성
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            this@SplashActivity.startActivity(
                Intent(
                    this@SplashActivity,
                    MainActivity::class.java
                ).apply {
                    putExtra(Const.FIRST_SPLASH, true)
                }
            )
            this@SplashActivity.finish()
        }
    }
}
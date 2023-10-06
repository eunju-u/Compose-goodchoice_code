package com.example.goodchoice.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.goodchoice.Const
import com.example.goodchoice.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.firstSplash = intent.getBooleanExtra(Const.FIRST_SPLASH, false)

        setContent {
            TestTheme {
                MainContent(viewModel = viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    val activityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK-> {
                    result.data?.let {
                        val isFiltered = it.getBooleanExtra("filtered", false)
                    }
                }
            }
        }
}
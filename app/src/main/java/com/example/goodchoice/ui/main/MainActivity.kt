package com.example.goodchoice.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.data.*
import com.example.goodchoice.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                MainContent(viewModel = viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestHomeData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
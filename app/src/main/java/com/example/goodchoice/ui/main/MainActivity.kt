package com.example.goodchoice.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.goodchoice.preference.GoodChoicePreference
import com.example.goodchoice.ui.theme.TestTheme
import kotlinx.coroutines.launch

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
        observerCurrentViewData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /** route 값이 변경되는지 observer 해서 데이터 조회함  **/
    private fun observerCurrentViewData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentRoute.collect { value ->
                    if (value.isNotEmpty()) {
                        viewModel.getCurrentViewData(this@MainActivity)
                    }
                }
            }
        }
    }
}
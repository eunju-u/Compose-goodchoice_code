package com.example.goodchoice.ui.recentSeen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.theme.TestTheme
import kotlinx.coroutines.launch

/**
 * 최근 본 상품 activity
 */
class RecentSeenActivity : ComponentActivity() {
    companion object {
        val TAG: String = RecentSeenActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                RecentSeenContent(viewModel = viewModel)
            }
        }
        observerRecentData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //MainViewModel 에서 최근 본 상품 가져오기 위해서 데이터 조회 함.
    private fun observerRecentData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeData.collect { value ->
                    value.recentStayList?.let {
                        if (it.isEmpty()) {
                            viewModel.requestHomeData()
                        }
                    }
                }
            }
        }
    }
}
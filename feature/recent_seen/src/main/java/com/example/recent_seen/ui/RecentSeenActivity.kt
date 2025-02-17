package com.example.recent_seen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.ui_theme.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * 최근 본 상품 activity
 */
@AndroidEntryPoint
class RecentSeenActivity : ComponentActivity() {
    companion object {
        val TAG: String = RecentSeenActivity::class.java.simpleName
    }

    private val viewModel: RecentSeenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.recentDb()
        setContent {
            TestTheme {
                RecentSeenContent(viewModel = viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
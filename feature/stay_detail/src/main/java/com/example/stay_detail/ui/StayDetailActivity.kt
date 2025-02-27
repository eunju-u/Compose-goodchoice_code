package com.example.stay_detail.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.Const
import com.example.common.intent.CommonIntent
import com.example.ui_theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StayDetailActivity : ComponentActivity() {
    companion object {
        val TAG: String = StayDetailActivity::class.java.simpleName
    }

    private val viewModel: StayDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.stayItemId = intent?.data?.getQueryParameter(Const.ITEM_ID) ?: ""
        viewModel.stayItemTitle = intent?.data?.getQueryParameter(Const.ITEM_TITLE) ?: ""

        setContent {
            TestTheme {
                StayDetailContent(
                    viewModel = viewModel,
                    onFinish = { this.finish() })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sendIntent(CommonIntent.LoadMyInfo)
        viewModel.requestStayDetail()
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.isLike.value) {
            viewModel.saveLike()
        }
    }
}
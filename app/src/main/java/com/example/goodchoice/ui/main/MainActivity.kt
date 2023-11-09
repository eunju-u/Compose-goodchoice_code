package com.example.goodchoice.ui.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.goodchoice.Const
import com.example.goodchoice.ui.search.data.KoreaSearchData
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

    //주변 화면에서 필터 > 숙소보기 클릭시 호출되는 콜백
    val activityForFilterResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    result.data?.let {
                        val isFiltered = it.getBooleanExtra("filtered", false)
                    }
                }
            }
        }

    //주변 화면 에서 지역, 숙소 검색 시 호출되는 콜백
    val activityForSearchResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    result.data?.let {
                        val data = it.getSerializableExtra(Const.DATA)
                        if (data != null) {
                            val searchItem = data as KoreaSearchData
                            searchItem.let { item ->
                                viewModel.selectSearchItem.value = item
                            }
                        }
                    }
                }
            }
        }
}
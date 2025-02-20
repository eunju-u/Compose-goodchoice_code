package com.example.goodchoice.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import com.example.common.Const
import com.example.common.ServerConst
import com.example.common.ui_data.AroundFilterItem
import com.example.domain.model.KoreaSearchData
import com.example.around.ui.AroundViewModel
import com.example.goodchoice.ui.home.HomeViewModel
import com.example.like.ui.LikeViewModel
import com.example.my_info.ui.MyInfoViewModel
import com.example.search.ui.SearchViewModel
import com.example.ui_theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val aroundViewModel: AroundViewModel by viewModels()
    private val likeViewModel: LikeViewModel by viewModels()
    private val myInfoViewModel: MyInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.firstSplash = intent.getBooleanExtra(Const.FIRST_SPLASH, false)

        setContent {
            TestTheme {
                MainContent(
                    mainViewModel = mainViewModel,
                    homeViewModel = homeViewModel,
                    searchViewModel = searchViewModel,
                    aroundViewModel = aroundViewModel,
                    likeViewModel = likeViewModel,
                    myInfoViewModel = myInfoViewModel
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //홈 -> 최근 본 상품 -> 전체 삭제 -> 홈 이동시 최근 본 상품 갱신하기 위함.
        homeViewModel.recentDb()
    }

    //주변 화면에서 필터 상세 > 주변화면 돌아올 경우 호출되는 콜백
    val activityForFilterResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    result.data?.let {
                        val data =
                            it.getSerializableExtra(Const.DATA) as ArrayList<AroundFilterItem>?

                        data?.let { list ->
                            list.forEach { item ->
                                val mutableItem = mutableStateOf(item)

                                when (item.mainType) {
                                    ServerConst.RESERVATION -> {
                                        aroundViewModel.aroundFilterSelect.selectedReservation =
                                            mutableItem
                                    }

                                    ServerConst.PRICE -> {
                                        aroundViewModel.aroundFilterSelect.selectedPrice = mutableItem
                                    }

                                    ServerConst.ROOM -> {
                                        aroundViewModel.aroundFilterSelect.selectedRoom = mutableItem
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    //주변 화면 에서 지역, 숙소 검색 시 호출되는 콜백
    val activityForSearchResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    result.data?.let {
                        val data = it.getSerializableExtra(Const.DATA)
                        if (data != null) {
                            val searchItem = data as KoreaSearchData
                            searchItem.let { item ->
                                aroundViewModel.selectSearchItem.value = item
                            }
                        }
                    }
                }
            }
        }

    //위치 권한 팝업 클릭시 호출되는 콜백
    val aroundRequestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        var isDenied = false
        for (entry in it.entries) {
            isDenied = entry.value == false
            break
        }
        if (isDenied) {
            // 팝업 노출
            mainViewModel.isShowDialog.value = true
        }
    }
}
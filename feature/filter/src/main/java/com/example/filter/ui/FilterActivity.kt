package com.example.filter.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.Const
import com.example.common.ui_data.AroundFilterSelectedModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.ui_theme.*
import com.google.gson.Gson

@AndroidEntryPoint
class FilterActivity : ComponentActivity() {
    companion object {
        val TAG: String = FilterActivity::class.java.simpleName
    }

    private val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //deeplink 데이터 수신
        val jsonData = intent?.data?.getQueryParameter(Const.DATA)
        val gson = Gson()
        val data : AroundFilterSelectedModel = try {
            if (jsonData.isNullOrEmpty()) {
                AroundFilterSelectedModel()
            } else {
                gson.fromJson(jsonData, AroundFilterSelectedModel::class.java)
            }
        }  catch (e: Exception) {
            AroundFilterSelectedModel()
        }
        viewModel.aroundSelectedData = data
        viewModel.requestFilterData(true)

        setContent {
            TestTheme {
                BackHandler(onBack = {
                    sendForActivity()
                    this.finish()
                })
                FilterContent(viewModel = viewModel, onFinish = {
                    sendForActivity()
                    this.finish()
                })
            }
        }
    }

    fun sendForActivity() {
        //전달할 데이터 리스트 갱신
        viewModel.setSelectFilterList()

        // mutableStateListOf 를 intent 에 넣고 데이터 받기
        val intent = Intent().apply {
            this.putExtra(Const.DATA, ArrayList(viewModel.selectFilterList))
        }
        setResult(RESULT_OK, intent)
    }
}
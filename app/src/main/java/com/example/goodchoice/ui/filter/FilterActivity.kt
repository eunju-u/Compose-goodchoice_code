package com.example.goodchoice.ui.filter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.common.Const
import com.example.common.theme.*
import com.example.goodchoice.domain.model.AroundFilterSelectedModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : ComponentActivity() {
    companion object {
        val TAG: String = FilterActivity::class.java.simpleName
    }

    private val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = AroundFilterSelectedModel()
        if (intent.hasExtra(Const.DATA)) {
            data = intent.getSerializableExtra(Const.DATA) as AroundFilterSelectedModel
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
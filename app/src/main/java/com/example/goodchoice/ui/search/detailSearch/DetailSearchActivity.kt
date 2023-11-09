package com.example.goodchoice.ui.search.detailSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.goodchoice.Const
import com.example.goodchoice.ui.theme.TestTheme

class DetailSearchActivity : ComponentActivity() {
    companion object {
        val TAG: String = DetailSearchActivity::class.java.simpleName
    }

    private val viewModel: DetailSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestSearchUiData()

        setContent {
            TestTheme {
                DetailSearchContent(
                    viewModel = viewModel,
                    onFinish = { this.finish() },
                    onSearchClick = {
                        val intent = Intent().apply {
                            this.putExtra(Const.DATA, it)
                        }
                        setResult(Activity.RESULT_OK, intent)
                        this.finish()
                    })
            }
        }
    }
}
package com.example.ui.stayDetail.service

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.common.Const
import com.example.ui.R
import com.example.ui.components.*
import com.example.domain.model.ServiceData
import com.example.ui.theme.*
import com.example.ui.utils.ConvertUtil

class ServiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val serviceData = intent.getSerializableExtra(Const.DATA) as ArrayList<ServiceData>
        setContent {
            TestTheme {
                Column {
                    TopAppBarWidget(
                        title = stringResource(id = R.string.str_convenience_service),
                        onFinish = { this@ServiceActivity.finish() })

                    LazyVerticalGrid(
                        modifier = Modifier.padding(
                            start = dp10,
                            end = dp10,
                            top = dp20,
                            bottom = dp20
                        ),
                        columns = GridCells.Fixed(count = 5),
                        verticalArrangement = Arrangement.spacedBy(dp20),
                    ) {
                        itemsIndexed(items = serviceData) { index, item ->
                            CategoryItemWidget(
                                painter = painterResource(
                                    id = ConvertUtil.convertServiceImage(
                                        item.type ?: ""
                                    )
                                ),
                                name = item.name ?: "",
                                colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                                imageSize = dp40,
                                textStyle = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
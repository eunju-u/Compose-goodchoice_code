package com.example.calendar.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui_common.R
import com.example.ui_theme.*

//기존 여기어때 앱은 인원 선택 뷰에서 해상도 고려 하지 않고 있다!
/**
 * 국내 캘린더 인원
 */
@Composable
fun KoreaPersonWidget(
    contentPadding: PaddingValues = PaddingValues(dp0), personCount: Int = 0,
    onLeftItemClick: () -> Unit = {}, onRightItemClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(top = dp20, start = dp25, end = dp25)
    ) {
        item {
            PersonWidget(
                title = stringResource(id = R.string.str_person),
                count =personCount,
                max = 10,
                description = stringResource(id = R.string.str_person_sub),
                onLeftItemClick = onLeftItemClick,
                onRightItemClick = onRightItemClick,
            )
        }
    }
}
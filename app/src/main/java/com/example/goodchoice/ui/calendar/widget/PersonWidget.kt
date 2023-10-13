package com.example.goodchoice.ui.calendar.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.ShapeButton
import com.example.goodchoice.ui.theme.*

//기존 여기어때 앱은 인원 선택 뷰에서 해상도 고려 하지 않고 있다!
@Composable
fun PersonWidget(
    contentPadding: PaddingValues = PaddingValues(dp0), personCount: Int = 0,
    onLeftItemClick: () -> Unit = {}, onRightItemClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(top = dp40)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dp20, end = dp20),
                horizontalArrangement = Arrangement.spacedBy(dp10)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.padding(bottom = dp3),
                        text = stringResource(id = R.string.str_person),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = stringResource(id = R.string.str_person_sub),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dp25),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShapeButton(
                        size = dp40,
                        isChecked = personCount > 1,
                        checkedColor = if (personCount > 1) Theme.colorScheme.pureBlue else Theme.colorScheme.pureGray,
                        borderColor = if (personCount > 1) Theme.colorScheme.blue else Theme.colorScheme.gray,
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_round_remove),
                                colorFilter = ColorFilter.tint(if (personCount > 1) Theme.colorScheme.blue else Theme.colorScheme.gray),
                                contentDescription = "빼기",
                            )
                        },
                        onItemClick = onLeftItemClick
                    )
                    Text(
                        text = if (personCount == 10) "$personCount+" else personCount.toString(),
                        style = MaterialTheme.typography.labelLarge
                    )
                    ShapeButton(
                        size = dp40,
                        isChecked = personCount < 10,
                        checkedColor = if (personCount < 10) Theme.colorScheme.pureBlue else Theme.colorScheme.pureGray,
                        borderColor = if (personCount < 10) Theme.colorScheme.blue else Theme.colorScheme.gray,
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_round_add),
                                colorFilter = ColorFilter.tint(if (personCount < 10) Theme.colorScheme.blue else Theme.colorScheme.gray),
                                contentDescription = "더하기",
                            )
                        },
                        onItemClick = onRightItemClick
                    )
                }
            }
        }
    }
}
package com.example.goodchoice.ui.myInfo.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.goodchoice.R
import com.example.goodchoice.api.data.MyMenuItem
import com.example.goodchoice.ui.components.SpaceBetweenRowWidget
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp10
import com.example.goodchoice.ui.theme.dp20


@Composable
/**
 * 내 정보 >> LazyColumn Custom Item
 *
 * "질문 있어요!"
 *
 * */
fun MenuItemWidget(menuItem: MyMenuItem){
    SpaceBetweenRowWidget(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = dp10, end = dp10)
            .background(Theme.colorScheme.pureBlue)
            .clickable {  },
//        text = menuItem.name,
//        textStyle = textStyle,
        content = {
            Row(
                modifier = Modifier
//                    .background(Theme.colorScheme.white)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                if(menuItem.icon != 0){
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier.size(dp20),
                            painter = painterResource(id = menuItem.icon),
                            contentDescription = null
                        )
                        Text(
                            text = menuItem.name
                        )
                        if (menuItem.subText != ""){
                            Text(
                                text = menuItem.subText,
                                fontSize = 9.sp,
                            )
                        }
                    }
                }else {
                    Text(
                        text = menuItem.name
                    )
                }
                Image(
                    colorFilter = ColorFilter.tint(Theme.colorScheme.gray),
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewMenuItemWidget() {
    MenuItemWidget(
        menuItem = MyMenuItem(id = 0, name = "테스트")
    )
}
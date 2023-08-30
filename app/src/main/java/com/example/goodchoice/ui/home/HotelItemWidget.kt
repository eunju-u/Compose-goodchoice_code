package com.example.goodchoice.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.goodchoice.data.StayItem
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.utils.StringUtil

@Composable
fun HotelItemWidget(stayItem: StayItem) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .border(
                width = 1.dp,
                color = Theme.colorScheme.pureGray,
                shape = RoundedCornerShape(10.dp)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        val padding = PaddingValues(start = 20.dp, end = 20.dp)
        val label = stayItem.label ?: ""
        val name = stayItem.name ?: ""
        val discountPer = stayItem.discountPer ?: 0
        val discountPrice = stayItem.defaultPrice ?: 0
        val convertDefaultPrice = StringUtil.convertCommaString(stayItem.defaultPrice ?: 0)
        val convertDiscountPrice = StringUtil.convertCommaString(stayItem.discountPrice ?: 0)
        val painter = rememberAsyncImagePainter(
            model = stayItem.imageUrl,
            error = painterResource(id = R.drawable.shape_yellow)
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            painter = painter,
            contentDescription = "호텔 사진"
        )

        Spacer(modifier = Modifier.height(5.dp))
        if (label.isNotEmpty()) {
            Text(modifier = Modifier.padding(padding), text = label)
            Spacer(modifier = Modifier.height(5.dp))
        }

        if (name.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(padding),
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
        }

        Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "별점"
            )
            Text(text = "${stayItem.star ?: 0}")
            Text(text = "(${stayItem.commentCount ?: 0})")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stayItem.location ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Theme.colorScheme.gray
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(modifier = Modifier.padding(padding), verticalAlignment = Alignment.CenterVertically) {
            if (discountPer > 0) {
                Text(text = "${discountPer}%", color = Theme.colorScheme.red)
                Spacer(modifier = Modifier.width(10.dp))
            }
            if (discountPrice > 0) {
                Text(
                    text = "$convertDefaultPrice ",
                    color = Theme.colorScheme.gray,
                    textDecoration = TextDecoration.LineThrough
                )
            } else {
                Text(text = "$convertDefaultPrice ")
            }
        }
        if (discountPrice > 0) {
            Text(modifier = Modifier.padding(padding), text = convertDiscountPrice)
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}
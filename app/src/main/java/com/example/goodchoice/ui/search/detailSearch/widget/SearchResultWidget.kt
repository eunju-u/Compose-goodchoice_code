package com.example.goodchoice.ui.search.detailSearch.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.TextWidget
import com.example.goodchoice.ui.search.data.KoreaSearchData
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.utils.StringUtil

@Composable
fun SearchResultWidget(
    modifier: Modifier = Modifier,
    item: KoreaSearchData = KoreaSearchData(),
    targetText: String = ""
) {
    val aroundImageVector = ImageVector.vectorResource(id = R.drawable.ic_around)
    val subwayImageVector = ImageVector.vectorResource(id = R.drawable.ic_subway)
    val hotelImageVector = ImageVector.vectorResource(id = R.drawable.ic_hotel)
    val imageVector =
        if (item.type == Const.PLACE) aroundImageVector
        else if (item.type == Const.STATION) subwayImageVector
        else hotelImageVector

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dp15)
    ) {
        Icon(
            modifier = Modifier
                .width(dp20)
                .height(dp20),
            imageVector = imageVector,
            contentDescription = null,
            tint = Theme.colorScheme.gray
        )

        Column(modifier = Modifier.weight(1f)) {
            TextWidget(
                text = StringUtil.setTextColor(
                    originText = item.name ?: "",
                    targetText = targetText
                ), style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(dp10))
            TextWidget(text = item.name ?: "", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(dp15))
            Divider(thickness = 1.5.dp, color = Theme.colorScheme.pureGray)
        }
    }
}
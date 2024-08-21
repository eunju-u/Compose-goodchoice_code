package com.example.goodchoice.ui.search.detailSearch.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.Const
import com.example.common.R
import com.example.common.components.TextWidget
import com.example.common.theme.Theme
import com.example.common.theme.*
import com.example.common.utils.StringUtil
import com.example.domain.model.KoreaSearchData

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
        when (item.type) {
            Const.PLACE -> aroundImageVector
            Const.STATION -> subwayImageVector
            else -> hotelImageVector
        }

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
            Spacer(modifier = Modifier.height(dp5))
            if (!item.location.isNullOrEmpty()) {
                TextWidget(
                    text = item.location ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    color = Theme.colorScheme.gray
                )
            }
            Spacer(modifier = Modifier.height(dp15))
            HorizontalDivider(thickness = 1.5.dp, color = Theme.colorScheme.pureGray)
        }
    }
}

@Preview
@Composable
fun PreviewSearchResultWidget() {
    SearchResultWidget(
        item = KoreaSearchData(
            name = "서현역",
            city = "서울",
            type = "station",
            location = "수도권 > 수인분당"
        )
    )
}
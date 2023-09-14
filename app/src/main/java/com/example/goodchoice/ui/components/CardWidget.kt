package com.example.goodchoice.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.theme.*


// 뷰의 width 값만 정해주고 height 값을 정하지 않는게 좋음
// 기존에 ButtonWidget 사용했는데 CardWidget 으로 바꾼 이유 :
// 해상도에 따라 text 의 font size 가 변경되기 때문에 뷰의 크기도 작아지게 하기 위함
@Composable
fun CardWidget(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(horizontal = dp10, vertical = dp12),
    outerPadding: PaddingValues = PaddingValues(dp0),
    isVisibleShadow: Boolean = false,
    borderWidth: Dp = 1.5.dp,
    borderColor: Color = Color.Transparent,
    containerColor: Color = Theme.colorScheme.white,
    cornerShape: RoundedCornerShape = RoundedCornerShape(dp0),
    alignment: Alignment = Alignment.TopStart,
    onItemClick: (() -> Unit)? = null,
    content: @Composable @UiComposable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .padding(outerPadding)
            .coloredShadow(
                color = if (isVisibleShadow) Theme.colorScheme.pureGray else Color.Transparent,
                blur = 15.dp,
                offsetY = 5.dp
            )
            .background(
                color = containerColor, shape = cornerShape
            )
            .border(shape = cornerShape, width = borderWidth, color = borderColor)
            .clip(shape = cornerShape)
            .clickable(enabled = onItemClick != null) {
                if (onItemClick != null) {
                    onItemClick()
                }
            }
            .padding(innerPadding),
        contentAlignment = alignment
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewCardView() {
    CardWidget(modifier = Modifier, alignment = Alignment.TopStart) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LeftImageButtonWidget(
                modifier = Modifier.weight(1f),
                isCenterHorizontalArrangement = false,
                containerColor = Theme.colorScheme.gray,
                contentColor = Theme.colorScheme.darkGray,
                title = "지도",
                onItemClick = {},
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_map),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.pureGray),
                        contentDescription = null
                    )
                })
            LeftImageButtonWidget(
                modifier = Modifier.weight(1f),
                isCenterHorizontalArrangement = false,
                containerColor = Theme.colorScheme.gray,
                contentColor = Theme.colorScheme.darkGray,
                title = "인원",
                onItemClick = {},
                content = {
                    Image(
                        modifier = Modifier.size(dp15),
                        painter = painterResource(id = R.drawable.ic_calendar),
                        colorFilter = ColorFilter.tint(Theme.colorScheme.pureGray),
                        contentDescription = null
                    )
                })
        }
    }
}
package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle

@Composable
fun HomeTopBarWidget(
    modifier: Modifier = Modifier,
    isTitleText: Boolean = true, //제목이 text 인지 image 인지
    title: String = "",
    titleStyle: TextStyle = MaterialTheme.typography.displayMedium,
    titleIcon: @Composable @UiComposable (() -> Unit)? = null,
    icon: @Composable @UiComposable (() -> Unit)? = null
) {
    var textStyle by remember { mutableStateOf(titleStyle) }

    Surface(modifier = modifier) {
        Box {
            Row(
                modifier = Modifier.matchParentSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isTitleText) {
                    Text(
                        text = title,
                        style = textStyle,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        onTextLayout = { textLayoutResult ->
                            if (textLayoutResult.didOverflowHeight) {
                                textStyle =
                                    textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                            }
                        })
                } else {
                    titleIcon?.run {
                        titleIcon()
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                icon?.run {
                    icon()
                }
            }
        }
    }
}
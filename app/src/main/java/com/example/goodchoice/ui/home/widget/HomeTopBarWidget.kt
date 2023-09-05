package com.example.goodchoice.ui.home.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.goodchoice.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle

@Composable
fun HomeTopBarWidget(
    modifier: Modifier = Modifier,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.displayMedium,
    onFinish: () -> Unit,
    isBackButton: Boolean = true,
    icon: @Composable @UiComposable (() -> Unit)? = null
) {
    var textStyle by remember { mutableStateOf(titleStyle) }

    Surface(modifier = modifier) {
        Box {
            if (isBackButton) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.clickable { onFinish() },
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier.matchParentSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            }

            if (icon != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    icon()
                }
            }
        }
    }
}
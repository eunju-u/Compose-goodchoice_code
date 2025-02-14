package com.example.webview.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui_common.R
import com.example.ui_theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopWebViewBarWidget(
    modifier: Modifier = Modifier,
    title: String = "",
    url: String = "",
    onBack: () -> Unit = {},
    rightContent: @Composable @UiComposable (() -> Unit)? = null
) {
    val displayMedium = MaterialTheme.typography.labelLarge
    var textStyle by remember { mutableStateOf(displayMedium) }

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        tint = Theme.colorScheme.gray,
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier.weight(1f, fill = false),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = textStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        onTextLayout = { textLayoutResult ->
                            if (textLayoutResult.didOverflowHeight) {
                                textStyle =
                                    textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                            }
                        })

                    if (url.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = url,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    if (rightContent != null) {
                        rightContent()
                    } else {
                        //rightContent 가 없을 때  title 이 중앙으로 오기 위해 더미 content 넣어준다.
                        IconButton(enabled = false, onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                tint = Theme.colorScheme.white,
                                contentDescription = null
                            )
                        }
                    }
                }

            }
        },
        navigationIcon = {
        },
        actions = {
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Theme.colorScheme.white,
            titleContentColor = Theme.colorScheme.darkGray,
            navigationIconContentColor = Theme.colorScheme.darkGray,
            actionIconContentColor = Theme.colorScheme.darkGray,
        )
    )
}
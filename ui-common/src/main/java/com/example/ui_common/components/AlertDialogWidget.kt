package com.example.ui_common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.ui_theme.Theme
import com.example.ui_theme.*

@Composable
fun AlertDialogWidget(
    title: String = "",
    content: String = "",
    onDismissRequest: () -> Unit = {},
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    oneButtonText: String = "",
    twoButtonText: String = "",
    textColor: Color = Theme.colorScheme.darkGray
) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(dp20),
                color = Theme.colorScheme.white
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(start = dp25, end = dp25)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (title.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dp25),
                            text = title,
                            style = MaterialTheme.typography.labelLarge,
                            color = textColor
                        )
                    } else {
                        Spacer(modifier = Modifier.height(dp20))
                    }

                    //content
                    if (content.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dp10),
                            text = content,
                            style = MaterialTheme.typography.labelMedium,
                            color = textColor
                        )
                    }

                    Spacer(modifier = Modifier.height(dp30))
                    if (oneButtonText.isNotEmpty() && twoButtonText.isEmpty())
                        OneButton(oneButtonText, onConfirm)
                    else
                        TwoButton(
                            oneButtonText,
                            twoButtonText,
                            onDismiss,
                            onConfirm
                        )
                    Spacer(modifier = Modifier.height(dp20))
                }
            }
        }
    }
}

@Composable
fun OneButton(
    buttonText: String = "",
    onConfirm: () -> Unit
) {
    val color = Theme.colorScheme.blue
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ButtonWidget(
            modifier = Modifier
                .fillMaxWidth(),
            onItemClick = onConfirm,
            containerColor = color,
            content = {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.labelLarge,
                    color = Theme.colorScheme.white
                )
            }
        )
    }
}

@Composable
fun TwoButton(
    oneButtonText: String = "",
    twoButtonText: String = "",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = dp10)
    ) {
        ButtonWidget(
            modifier = Modifier
                .weight(1f),
            onItemClick = onDismiss,
            containerColor = Theme.colorScheme.pureGray,
            content = {
                Text(
                    text = oneButtonText,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = Theme.colorScheme.gray
                )
            }
        )

        CardWidget(
            modifier = Modifier
                .weight(1f),
            onItemClick = onConfirm,
            containerColor = Theme.colorScheme.blue,
            alignment = Alignment.Center,
            cornerShape = RoundedCornerShape(dp10),
            content = {
                Text(
                    text = twoButtonText,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = Theme.colorScheme.white
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreViewAlertDialogWidget() {
    AlertDialogWidget(
        title = "로그인",
        content = "",
        onDismissRequest = {},
        onDismiss = {},
        onConfirm = {},
        oneButtonText = "로그인",
        twoButtonText = "로그인",
    )
}


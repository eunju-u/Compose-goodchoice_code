package com.example.goodchoice.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.goodchoice.ui.theme.*
import com.example.goodchoice.R

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
                        .padding(PaddingValues(start = dp20, end = dp20)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (title.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dp20),
                            text = title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                            ),
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
        CardWidget(
            modifier = Modifier
                .fillMaxWidth(),
            onItemClick = onConfirm,
            containerColor = color,
            cornerShape = RoundedCornerShape(dp0),
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
    val color = Theme.colorScheme.blue

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = dp10)
    ) {
        CardWidget(
            modifier = Modifier
                .weight(1f),
            onItemClick = onDismiss,
            containerColor = color,
            alignment = Alignment.Center,
            cornerShape = RoundedCornerShape(dp10),
            content = {
                Text(
                    text = oneButtonText, style = MaterialTheme.typography.labelLarge,
                    color = Theme.colorScheme.white
                )
            }
        )

        CardWidget(
            modifier = Modifier
                .weight(1f),
            onItemClick = onConfirm,
            containerColor = color,
            alignment = Alignment.Center,
            cornerShape = RoundedCornerShape(dp10),
            content = {
                Text(
                    text = twoButtonText, style = MaterialTheme.typography.labelLarge,
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
        title = stringResource(id = R.string.str_login),
        content = "",
        onDismissRequest = {},
        onDismiss = {},
        onConfirm = {},
        oneButtonText = stringResource(id = R.string.str_login),
        twoButtonText = stringResource(id = R.string.str_login),
    )
}


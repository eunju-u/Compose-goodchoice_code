package com.example.goodchoice.ui.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.goodchoice.R
import com.example.goodchoice.ui.login.LoginActivity
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp10

@Composable
fun GoLoginTextWidget(firstText: String = "", secondText: String = "") {
    val context = LocalContext.current

    Text(
        modifier = Modifier,
        text = firstText,
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
        color = Theme.colorScheme.gray
    )
    Spacer(modifier = Modifier.height(dp10))
    Text(
        modifier = Modifier,
        text = secondText,
        style = MaterialTheme.typography.labelLarge,
        color = Theme.colorScheme.gray
    )
    Spacer(modifier = Modifier.height(dp10))

    Text(
        modifier = Modifier.clickable {
            context.startActivity(
                Intent(
                    context,
                    LoginActivity::class.java
                )
            )
        },
        text = stringResource(id = R.string.str_login),
        style = MaterialTheme.typography.labelMedium,
        color = Theme.colorScheme.panoramaBlue
    )
}

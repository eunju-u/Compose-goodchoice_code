package com.example.goodchoice.ui.main.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.ButtonWidget
import com.example.goodchoice.ui.theme.Theme
import com.example.goodchoice.ui.theme.dp10
import com.example.goodchoice.ui.theme.dp20

/**
 * 프로필 사진 선택 바텀 시트
 * **/
@Composable
fun ProfileContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(dp20)
    ) {
        Text(
            modifier = Modifier.padding(top = dp10, bottom = dp10),
            text = stringResource(id = R.string.str_profile_register),
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            modifier = Modifier.padding(top = dp10, bottom = dp10),
            text = stringResource(id = R.string.str_profile_register_sub),
            color = Theme.colorScheme.darkGray,
            style = MaterialTheme.typography.labelLarge
        )


        ButtonWidget(
            modifier = Modifier
                .fillMaxWidth(),
            onItemClick = {
                //TODO 갤러리
            },
            containerColor = Theme.colorScheme.blue,
            content = {
                Text(
                    text = stringResource(id = R.string.str_select_picture),
                    style = MaterialTheme.typography.labelLarge,
                    color = Theme.colorScheme.white
                )
            }
        )
    }
}

@Preview
@Composable
fun PreviewProfileContent() {
    ProfileContent()
}
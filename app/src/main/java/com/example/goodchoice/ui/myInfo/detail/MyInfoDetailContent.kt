package com.example.goodchoice.ui.myInfo.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ui.R
import com.example.ui.components.*
import com.example.ui_theme.*
import com.example.data.local.preference.GoodChoicePreference
import com.example.ui.components.TopAppBarWidget
import com.example.ui.utils.ConvertUtil

@Composable
fun MyInfoDetailContent(onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    val pref = GoodChoicePreference(context)

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colorScheme.white)

        ) {
            TopAppBarWidget(
                title = stringResource(id = R.string.str_my_info_manager),
                isCloseButton = false,
                onFinish = { onFinish() })

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    item {
                        CardWidget(
                            isVisibleShadow = true,
                            shadowOffsetY = dp20,
                            shadowColor = Theme.colorScheme.pureGray,
                            innerPadding = PaddingValues(start = dp20, end = dp20, top = dp20),
                            containerColor = Theme.colorScheme.white,
                            content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Min),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(dp80),
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_smile),
                                        tint = Theme.colorScheme.red,
                                        contentDescription = "내 정보",
                                    )

                                    Column(
                                        modifier = Modifier.weight(1f),
                                    ) {

                                        RightImageButtonWidget(
                                            title = pref.userName,
                                            style = MaterialTheme.typography.displaySmall,
                                            content = {
                                                Icon(
                                                    modifier = Modifier.size(dp20),
                                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_pencil),
                                                    tint = Theme.colorScheme.gray,
                                                    contentDescription = "수정",
                                                )
                                            }
                                        )

                                        TextWidget(
                                            modifier = Modifier
                                                .padding(top = dp5, start = dp12)
                                                .background(Theme.colorScheme.mediumPurple)
                                                .padding(dp3),
                                            textAlign = TextAlign.Start,
                                            color = Theme.colorScheme.darkPurple,
                                            maxLines = 2,
                                            text = stringResource(
                                                id = ConvertUtil.convertLoginState(
                                                    pref.loginWay
                                                )
                                            ),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}
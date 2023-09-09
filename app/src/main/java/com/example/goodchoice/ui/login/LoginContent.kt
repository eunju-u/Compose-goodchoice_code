package com.example.goodchoice.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun LoginContent(modifier: Modifier = Modifier, onFinish: () -> Unit = {}) {
    val style = MaterialTheme.typography.labelMedium
    val iconModifier = Modifier.size(dp15)
    val endPadding = dp10
    val outsideModifier = Modifier.padding(bottom = dp15)
    val widgetSize = Modifier
        .fillMaxWidth()
        .height(dp50)

    val isLoginKakao = false
    val isLoginNaver = true
    val isLoginFacebook = false
    val isLoginApple = false
    val isLoginEmail = false

    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.colorScheme.white)
    ) {
        Column {
            TopAppBarWidget(onFinish = { onFinish() }) {}
            LazyColumn(
                modifier.padding(start = dp40, end = dp40),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {

                    Text(
                        modifier = Modifier.padding(top = dp100),
                        text = stringResource(id = R.string.str_app_name),
                        style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                        color = Theme.colorScheme.red,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dp35, bottom = dp25),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(
                            modifier = Modifier.weight(1f, fill = false),
                            thickness = dp1,
                            color = Theme.colorScheme.pureGray
                        )
                        Text(
                            text = stringResource(id = R.string.str_login_and_join),
                            color = Theme.colorScheme.gray,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Divider(
                            modifier = Modifier.weight(1f, fill = false),
                            thickness = dp1,
                            color = Theme.colorScheme.pureGray
                        )
                    }

                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outsideModifier = outsideModifier,
                        title = stringResource(id = R.string.str_login_kakao),
                        containerColor = Theme.colorScheme.yellow,
                        contentColor = Theme.colorScheme.darkGray,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_kakao),
                                contentDescription = null
                            )
                        },
                        onItemClick = { })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outsideModifier = outsideModifier,
                        title = stringResource(id = R.string.str_login_naver),
                        containerColor = Theme.colorScheme.green,
                        contentColor = Theme.colorScheme.white,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_naver),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.white),
                                contentDescription = null
                            )
                        },
                        onItemClick = { })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outsideModifier = outsideModifier,
                        title = stringResource(id = R.string.str_login_facebook),
                        containerColor = Theme.colorScheme.blue,
                        contentColor = Theme.colorScheme.white,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = null
                            )
                        },
                        onItemClick = { })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outsideModifier = outsideModifier,
                        title = stringResource(id = R.string.str_login_apple),
                        containerColor = Theme.colorScheme.darkGray,
                        contentColor = Theme.colorScheme.white,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_apple),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.white),
                                contentDescription = null
                            )
                        },
                        onItemClick = { })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outsideModifier = outsideModifier,
                        title = stringResource(id = R.string.str_login_email),
                        containerColor = Theme.colorScheme.pureBlue,
                        contentColor = Theme.colorScheme.blue,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_email),
                                colorFilter = ColorFilter.tint(Theme.colorScheme.blue),
                                contentDescription = null
                            )
                        },
                        onItemClick = { })
                }
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(dp30)
                .clickable { },
            text = stringResource(id = R.string.str_login_business),
            color = Theme.colorScheme.darkGray,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        var recentViewTopPadding = 215.dp
        val plusPadding = 65.dp
        if (isLoginNaver) {
            recentViewTopPadding += plusPadding * 1
        } else if (isLoginFacebook) {
            recentViewTopPadding += plusPadding * 2
        } else if (isLoginApple) {
            recentViewTopPadding += plusPadding * 3
        } else if (isLoginEmail) {
            recentViewTopPadding += plusPadding * 4
        }
        //최근에 로그인했어요 뷰
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = recentViewTopPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .offset(y = (10).dp)
                        .clip(RoundedCornerShape(dp20))
                        .background(color = Theme.colorScheme.darkGray)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(dp8),
                        text = stringResource(R.string.str_login_recent),
                        style = MaterialTheme.typography.labelMedium.copy(color = Theme.colorScheme.pureGray)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                    colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                    contentDescription = null
                )
            }

        }
    }
}
package com.example.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.Const
import com.example.ui.R
import com.example.ui.components.*
import com.example.ui.theme.*

@Composable
fun LoginContent(isLoginWay: String = "", onFinish: (loginWay: String) -> Unit = {}) {
    val style = MaterialTheme.typography.labelMedium
    val iconModifier = Modifier.size(dp15)
    val endPadding = dp10
    val outsidePadding = PaddingValues(bottom = dp12)
    val innerPadding = PaddingValues(horizontal = com.example.ui.theme.dp15, vertical = com.example.ui.theme.dp15)
    val widgetSize = Modifier
        .fillMaxWidth()

    var plusPadding by remember { mutableStateOf(58.5.dp) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    //해상도에 따라 이미지 버튼의 크기가 달라짐 (CardWidget 이라서)
    //달라짐에 따라 '최근에 로그인했어요' 뷰 이동 처리
    //해당 내용 추가 하지 않으려면 정적인 ButtonWidget 나 height 줘서 써야함.
    LaunchedEffect(key1 = screenWidth) {
        if (isPortrait) {
            plusPadding = if (screenWidth < 400.dp) {
                if (style.fontSize < 14.sp) {
                    (plusPadding.value * 0.97).dp
                } else {
                    (plusPadding.value * com.example.ui.theme.fontSmallSizeRaio).dp
                }
            } else {
                plusPadding
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(com.example.ui.theme.Theme.colorScheme.white)
    ) {
        Column {
            TopAppBarWidget(isCloseButton = true, onFinish = { onFinish("") }) {}
            LazyColumn(
                Modifier.padding(start = com.example.ui.theme.dp20, end = com.example.ui.theme.dp20),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        modifier = Modifier
                            .size(com.example.ui.theme.dp130)
                            .padding(top = com.example.ui.theme.dp25),
                        painter = painterResource(id = R.drawable.img_goodchoice),
                        contentDescription = stringResource(id = R.string.str_app_name)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = com.example.ui.theme.dp25, bottom = com.example.ui.theme.dp25),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f, fill = false),
                            thickness = com.example.ui.theme.dp1,
                            color = com.example.ui.theme.Theme.colorScheme.pureGray
                        )
                        Text(
                            text = stringResource(id = R.string.str_login_and_join),
                            color = com.example.ui.theme.Theme.colorScheme.gray,
                            style = MaterialTheme.typography.labelMedium
                        )
                        HorizontalDivider(
                            modifier = Modifier.weight(1f, fill = false),
                            thickness = com.example.ui.theme.dp1,
                            color = com.example.ui.theme.Theme.colorScheme.pureGray
                        )
                    }

                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outerPadding = outsidePadding,
                        innerPadding = innerPadding,
                        title = stringResource(id = R.string.str_start_login_kakao),
                        containerColor = com.example.ui.theme.Theme.colorScheme.yellow,
                        contentColor = com.example.ui.theme.Theme.colorScheme.darkGray,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_kakao),
                                colorFilter = ColorFilter.tint(com.example.ui.theme.Theme.colorScheme.darkGray),
                                contentDescription = null
                            )
                        },
                        onItemClick = {
                            onFinish(Const.KAKAO)
                        })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outerPadding = outsidePadding,
                        innerPadding = innerPadding,
                        title = stringResource(id = R.string.str_start_login_naver),
                        containerColor = com.example.ui.theme.Theme.colorScheme.green,
                        contentColor = com.example.ui.theme.Theme.colorScheme.white,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_naver),
                                colorFilter = ColorFilter.tint(com.example.ui.theme.Theme.colorScheme.white),
                                contentDescription = null
                            )
                        },
                        onItemClick = {
                            onFinish(Const.NAVER)
                        })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outerPadding = outsidePadding,
                        innerPadding = innerPadding,
                        title = stringResource(id = R.string.str_start_login_facebook),
                        containerColor = com.example.ui.theme.Theme.colorScheme.blue,
                        contentColor = com.example.ui.theme.Theme.colorScheme.white,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = null
                            )
                        },
                        onItemClick = {
                            onFinish(Const.FACEBOOK)
                        })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outerPadding = outsidePadding,
                        innerPadding = innerPadding,
                        title = stringResource(id = R.string.str_start_login_google),
                        contentColor = com.example.ui.theme.Theme.colorScheme.darkGray,
                        borderColor = com.example.ui.theme.Theme.colorScheme.pureGray,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = null
                            )
                        },
                        onItemClick = {
                            onFinish(Const.GOOGLE)
                        })
                    LeftImageButtonWidget(
                        modifier = widgetSize,
                        outerPadding = outsidePadding,
                        innerPadding = innerPadding,
                        title = stringResource(id = R.string.str_start_login_email),
                        containerColor = com.example.ui.theme.Theme.colorScheme.pureBlue,
                        contentColor = com.example.ui.theme.Theme.colorScheme.blue1,
                        style = style,
                        endPadding = endPadding,
                        content = {
                            Image(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_email),
                                colorFilter = ColorFilter.tint(com.example.ui.theme.Theme.colorScheme.blue),
                                contentDescription = null
                            )
                        },
                        onItemClick = {
                            onFinish(Const.EMAIL)
                        })
                }
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(com.example.ui.theme.dp30)
                .clickable { },
            text = stringResource(id = R.string.str_login_business),
            color = com.example.ui.theme.Theme.colorScheme.darkGray,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        var recentViewTopPadding = 197.dp
        if (isLoginWay == Const.NAVER) {
            recentViewTopPadding += plusPadding * 1
        } else if (isLoginWay == Const.FACEBOOK) {
            recentViewTopPadding += plusPadding * 2
        } else if (isLoginWay == Const.GOOGLE) {
            recentViewTopPadding += plusPadding * 3
        } else if (isLoginWay == Const.EMAIL) {
            recentViewTopPadding += plusPadding * 4
        }
        //최근에 로그인했어요 뷰
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = recentViewTopPadding, start = com.example.ui.theme.dp15, end = com.example.ui.theme.dp15),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .offset(y = (10).dp)
                        .clip(RoundedCornerShape(com.example.ui.theme.dp20))
                        .background(color = com.example.ui.theme.Theme.colorScheme.darkGray)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(com.example.ui.theme.dp8),
                        text = stringResource(R.string.str_login_recent),
                        style = MaterialTheme.typography.labelMedium.copy(color = com.example.ui.theme.Theme.colorScheme.pureGray)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                    colorFilter = ColorFilter.tint(com.example.ui.theme.Theme.colorScheme.darkGray),
                    contentDescription = null
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewLoginContent() {
    LoginContent()
}
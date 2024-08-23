package com.example.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.Theme
import com.example.ui.theme.*

@Composable
fun LeftImageEditTextWidget(
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(dp0),
    innerPadding: PaddingValues = PaddingValues(horizontal = dp15, vertical = dp15),
    hint: String = "",
    hasEndPadding: Boolean = true,
    endPadding: Dp = dp5,
    shape: Dp = dp10,
    borderWidth: Dp = 1.5.dp,
    containerColor: Color = Theme.colorScheme.white,
    borderColor: Color = Color.Transparent,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    @DrawableRes vectorImageId: Int? = null,
    keyboardController: SoftwareKeyboardController? = null,
    isVisibleShadow: Boolean = false,
    focusChanged: (Boolean) -> Unit = {},
    onInputChanged: (String) -> Unit = {},
    isShowDialog: (Boolean) -> Unit = {},
    content: @Composable @UiComposable (() -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current
    var textFieldState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val pureGrayColor = Theme.colorScheme.pureGray
    val grayColor = Theme.colorScheme.gray
    val darkColor = Theme.colorScheme.darkGray
    var iconColor by remember { mutableStateOf(pureGrayColor) }
    var isShowClose by remember { mutableStateOf(false) }
    val imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle_close)

    CardWidget(
        modifier = modifier,
        innerPadding = outerPadding,
        isVisibleShadow = isVisibleShadow,
        shadowOffsetY = dp8,
    ) {
        Surface(
            modifier = modifier
                .wrapContentHeight()
                .background(
                    color = containerColor, shape = RoundedCornerShape(shape)
                )
                .border(shape = RoundedCornerShape(shape), width = borderWidth, color = borderColor)
                .clip(shape = RoundedCornerShape(shape))
                .padding(innerPadding)
                .onFocusChanged {
                    focusChanged(it.hasFocus)
                    //포커스가 가게 되면 아이콘 색상이 변경됨
                    iconColor = if (it.hasFocus) darkColor else grayColor
                    isShowClose = it.hasFocus
                    if (!it.hasFocus) {
                        keyboardController?.hide()
                    }
                },
            color = containerColor
        ) {
            //** 가장자리에 아이콘 제외하고 나머지 부분 Text 로 채우기
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (vectorImageId != null) {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        painter = painterResource(id = vectorImageId),
                        tint = iconColor,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                }
                if (content != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (hasEndPadding) endPadding else dp0),
                        contentAlignment = Alignment.Center
                    ) { content() }
                }

                BasicTextField(modifier = Modifier.weight(1f),
                    value = textFieldState,
                    onValueChange = {
                        textFieldState = it
                        onInputChanged(textFieldState.text)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(
                        onGo = {
                            // 두글자 미만시 다이얼로그 노출
                            if (textFieldState.text.length < 2) {
                                isShowDialog(true)
                            } else {
                                onInputChanged(textFieldState.text)
                                keyboardController?.hide()
                            }
                        }
                    ),
                    textStyle = style.copy(color = Theme.colorScheme.darkGray),
                    cursorBrush = SolidColor(LocalContentColor.current),
                    decorationBox = { innerTextField ->
                        if (hint.isNotEmpty() && textFieldState.text.isEmpty()) {
                            Text(
                                text = hint,
                                style = style,
                                color = Theme.colorScheme.gray
                            )
                        }
                        innerTextField()
                    }
                )

                //검색 닫기 뷰
                if (isShowClose) {
                    Icon(
                        modifier = Modifier
                            .width(dp20)
                            .height(dp20)
                            .clip(shape = RoundedCornerShape(dp50))
                            .clickable {
                                //키보드 내리기
                                keyboardController?.hide()
                                //포커스 강제 해제하기
                                focusManager.clearFocus()
                            },
                        imageVector = imageVector,
                        contentDescription = null,
                        tint = Theme.colorScheme.gray
                    )
                }
            }
        }
    }
}
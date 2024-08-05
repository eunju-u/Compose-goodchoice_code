package com.example.goodchoice.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.common.MainBottomSheetType
import com.example.goodchoice.ConnectInfo
import com.example.common.R
import com.example.common.components.*
import com.example.common.theme.GMarketSansFamily
import com.example.common.theme.Theme
import com.example.common.theme.*
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetLayout
import com.example.goodchoice.ui.components.bottomSheet.SheetWidget
import com.example.goodchoice.ui.main.bottomSheet.ProfileContent
import com.example.goodchoice.ui.main.nav.NavGraph
import com.example.goodchoice.ui.main.nav.NavItem
import com.example.goodchoice.ui.main.nav.navigation
import com.example.goodchoice.ui.main.state.rememberMainState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val mainState = rememberMainState()
    val navBackStackEntry by mainState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()

    val style = TextStyle(
        fontSize = 9.sp,
        lineHeight = 10.sp,
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
    )
    var textStyle by remember { mutableStateOf(style) }

    var bottomSheetType by remember { mutableStateOf(MainBottomSheetType.NONE) }

    var isShowDialog = viewModel.isShowDialog.collectAsStateWithLifecycle()

    /** route 값이 변경되면 데이터 조회함  **/
    LaunchedEffect(Unit) {
        viewModel.currentRoute.collectLatest {
            if (it.isNotEmpty()) {
                viewModel.getCurrentViewData(context)
            }
        }
    }

    /** 바텀 시트 상태값이 변경되면 노출됨. **/
    LaunchedEffect(Unit) {
        viewModel.bottomSheetType.collectLatest {
            bottomSheetType = it
            if (it != MainBottomSheetType.NONE) {
                with(mainState) {
                    scope.launch { bottomSheetState.show() }
                }
            }
        }
    }

    Box {
        Scaffold(
            scaffoldState = mainState.scaffoldState,
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.height(dp50),
                    backgroundColor = Theme.colorScheme.white,
                    contentColor = Theme.colorScheme.darkGray,
                    elevation = 12.dp
                ) {
                    mainState.bottomMenus.value.forEachIndexed { _, item ->
                        BottomNavigationItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                with(mainState) {
                                    navController.navigation(item)
                                }
                            },
                            icon = {
                                Box {
                                    Icon(
                                        painter = painterResource(id = item.icon),
                                        contentDescription = stringResource(id = item.title),
                                        tint = if (currentRoute == item.route) {
                                            Theme.colorScheme.red
                                        } else {
                                            Theme.colorScheme.gray
                                        }
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.title),
                                    color = if (currentRoute == item.route) {
                                        Theme.colorScheme.darkGray
                                    } else {
                                        Theme.colorScheme.gray
                                    },
                                    maxLines = 1,
                                    style = textStyle,
                                    onTextLayout = { textLayoutResult ->
                                        if (textLayoutResult.didOverflowHeight) {
                                            textStyle =
                                                textStyle.copy(fontSize = textStyle.fontSize * 0.98)
                                        }
                                    }
                                )
                            },
                            selectedContentColor = Theme.colorScheme.darkGray,
                            unselectedContentColor = Theme.colorScheme.gray
                        )
                    }
                }
            },
            backgroundColor = Theme.colorScheme.white,
            contentColor = Theme.colorScheme.darkGray
        ) { paddingValues ->
            //현재 navigation 값 viewModel 에 저장
            viewModel.currentRoute.value = currentRoute ?: NavItem.Home.route

            BackHandler {
                if (mainState.bottomSheetState.isVisible) {
                    mainState.scope.launch {
                        viewModel.bottomSheetType.value = MainBottomSheetType.NONE
                        mainState.bottomSheetState.hide()
                    }
                } else {
                    if (currentRoute == NavItem.Home.route) {
                        (context as MainActivity).finish()
                    } else {
                        mainState.navController.navigation(NavItem.Home)
                    }
                }
            }

            NavGraph(
                modifier = Modifier.padding(paddingValues),
                navController = mainState.navController,
                startDestination = NavItem.Home.route,
                viewModel = viewModel
            )
        }

        //바텀시트 노출
        MyBottomSheetLayout(
            sheetState = mainState.bottomSheetState,
            sheetContent = {
                if (bottomSheetType == MainBottomSheetType.PROFILE) {
                    SheetWidget(
                        shape = RoundedCornerShape(topStart = dp100, topEnd = dp100),
                        content = {
                            ProfileContent()
                        })
                }
            },
            onDismiss = {
                viewModel.bottomSheetType.value = MainBottomSheetType.NONE
            })

        if (homeUiState.value is ConnectInfo.Loading) {
            LoadingWidget()
        }
    }

    if (isShowDialog.value) {
        AlertDialogWidget(
            title = stringResource(id = R.string.str_allow_permission),
            oneButtonText = stringResource(id = R.string.str_close),
            twoButtonText = stringResource(id = R.string.str_set_permission),
            onDismiss = {
                viewModel.isShowDialog.value = false
            },
            onConfirm = {
                viewModel.isShowDialog.value = false
                //시스템 권한 설정으로 이동
                context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                })
            })
    }
}
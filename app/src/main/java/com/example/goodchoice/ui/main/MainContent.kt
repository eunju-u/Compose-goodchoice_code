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
import androidx.compose.ui.UiComposable
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
import com.example.common.Const
import com.example.common.MainBottomSheetType
import com.example.common.ui_data.AroundFilterSelectedModel
import com.example.ui_common.R
import com.example.domain.info.ConnectInfo
import com.example.ui_common.components.bottomSheet.MyBottomSheetLayout
import com.example.ui_common.components.bottomSheet.SheetWidget
import com.example.goodchoice.ui.main.bottomSheet.ProfileContent
import com.example.goodchoice.nav.NavGraph
import com.example.goodchoice.nav.NavItem
import com.example.goodchoice.nav.navigation
import com.example.goodchoice.ui.around.AroundViewModel
import com.example.goodchoice.ui.home.HomeViewModel
import com.example.goodchoice.ui.like.LikeViewModel
import com.example.goodchoice.ui.main.state.rememberMainState
import com.example.my_info.ui.MyInfoViewModel
import com.example.search.ui.SearchViewModel
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_theme.*
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    mainViewModel: MainViewModel,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    aroundViewModel: AroundViewModel,
    likeViewModel: LikeViewModel,
    myInfoViewModel: MyInfoViewModel,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val mainState = rememberMainState()
    val navBackStackEntry by mainState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val homeUiState = mainViewModel.homeUiState.collectAsStateWithLifecycle()

    val style = TextStyle(
        fontSize = 9.sp,
        lineHeight = 10.sp,
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
    )
    var textStyle by remember { mutableStateOf(style) }

    var bottomSheetType by remember { mutableStateOf(MainBottomSheetType.PROFILE) }

    var isShowDialog = mainViewModel.isShowDialog.collectAsStateWithLifecycle()

    /** route 값이 변경되면 데이터 조회함  **/
    LaunchedEffect(Unit) {
        mainViewModel.currentRoute.collectLatest {
            if (it.isNotEmpty()) {
                when (mainViewModel.currentRoute.value) {
                    NavItem.Home.route -> {
                        homeViewModel.requestHomeData()
                        homeViewModel.recentDb()
                    }

                    NavItem.Search.route -> {
                        searchViewModel.requestSearchData()
                    }

                    NavItem.Around.route -> {
                        aroundViewModel.requestAroundData(true)
                    }

                    NavItem.Like.route -> {
                        likeViewModel.requestLikeData(context)
                    }

                    NavItem.MyInfo.route -> {
                        myInfoViewModel.requestMyInfoData()
                    }
                }
            }
        }
    }

    /** 바텀시트에 노출되는 UI  **/
    val sheet: @Composable @UiComposable ColumnScope.() -> Unit = when (bottomSheetType) {
        MainBottomSheetType.PROFILE -> {
            {
                SheetWidget(
                    shape = RoundedCornerShape(
                        topStart = dp100,
                        topEnd = dp100
                    ),
                    content = {
                        ProfileContent()
                    })
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
            mainViewModel.currentRoute.value = currentRoute ?: NavItem.Home.route

            BackHandler {
                if (mainState.bottomSheetState.isVisible) {
                    mainState.scope.launch {
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
                homeViewModel = homeViewModel,
                searchViewModel = searchViewModel,
                aroundViewModel = aroundViewModel,
                likeViewModel = likeViewModel,
                myInfoViewModel = myInfoViewModel,
                showBottomSheet = { type ->
                    bottomSheetType = type
                    with(mainState) {
                        scope.launch { mainState.bottomSheetState.show() }
                    }
                },
                showFilter = {
                    // viewModel.aroundFilterSelect 의 데이터는 mutable 타입이라 serialize 불가
                    val selectedData = aroundViewModel.aroundFilterSelect
                    val model =
                        AroundFilterSelectedModel(
                            selectedData.selectedFilter.value,
                            selectedData.selectedRecommend.value,
                            selectedData.selectedRoom.value,
                            selectedData.selectedReservation.value,
                            selectedData.selectedPrice.value
                        )

                    val gson = Gson()
                    val jsonData = gson.toJson(model)
                    val deepLinkUri = Uri.Builder()
                        .scheme("feature")
                        .authority("filter")
                        .appendQueryParameter(Const.DATA, jsonData)
                        .build()

                    (context as MainActivity).activityForFilterResult.launch(
                        Intent(Intent.ACTION_VIEW, deepLinkUri)
                    )
                }
            )
        }

        //바텀시트 노출
        MyBottomSheetLayout(
            sheetState = mainState.bottomSheetState,
            sheetContent = sheet
        )

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
                mainViewModel.isShowDialog.value = false
            },
            onConfirm = {
                mainViewModel.isShowDialog.value = false
                //시스템 권한 설정으로 이동
                context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                })
            })
    }
}
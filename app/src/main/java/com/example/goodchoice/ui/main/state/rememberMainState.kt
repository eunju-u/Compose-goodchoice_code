package com.example.goodchoice.ui.main.state

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.goodchoice.ui.main.nav.NavItem
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberMainState(
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    bottomMenus: MutableState<List<NavItem>> = remember {
        mutableStateOf(
            listOf(
                NavItem.Home,
                NavItem.Search,
                NavItem.Around,
                NavItem.Like,
                NavItem.MyInfo
            )
        )
    },
    scaffoldState: ScaffoldState = rememberScaffoldState()
): MainState = remember {
    MainState(
        navController = navController,
        bottomSheetState = bottomSheetState,
        scope = scope,
        bottomMenus = bottomMenus,
        scaffoldState = scaffoldState
    )
}
package com.example.goodchoice.ui.main.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetState
import com.example.goodchoice.ui.main.nav.NavItem
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Stable
class MainState  constructor(
    val navController: NavHostController,
    val bottomSheetState: MyBottomSheetState,
    val bottomMenus: MutableState<List<NavItem>>,
    val scaffoldState: ScaffoldState,
    val scope: CoroutineScope,
)
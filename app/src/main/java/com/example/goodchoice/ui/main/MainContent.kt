package com.example.goodchoice.ui.main

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.goodchoice.ui.main.nav.NavGraph
import com.example.goodchoice.ui.main.nav.NavItem
import com.example.goodchoice.ui.main.nav.navigation
import com.example.goodchoice.ui.main.state.rememberMainState
import com.example.goodchoice.ui.theme.GMarketSansFamily
import com.example.goodchoice.ui.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val mainState = rememberMainState()
    val navBackStackEntry by mainState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var titleText by remember { mutableStateOf("") }

    val style = TextStyle(
        fontSize = 10.sp,
        lineHeight = 10.sp,
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
    )
    var textStyle by remember { mutableStateOf(style) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                titleText = ""
            }
        }

    Box {
        Scaffold(
            scaffoldState = mainState.scaffoldState,
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.height(60.dp),
                    backgroundColor = Theme.colorScheme.white,
                    contentColor = Theme.colorScheme.darkGray,
                    elevation = 12.dp
                ) {
                    mainState.bottomMenus.value.forEachIndexed { _, item ->
                        val tintColor = if (currentRoute == item.route) {
                            Theme.colorScheme.darkGray
                        } else {
                            Theme.colorScheme.pureGray
                        }

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
                                        tint = tintColor
                                    )
                                    if (currentRoute == item.route) {
                                        Badge(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .align(Alignment.TopEnd)
                                                .offset(x = 3.dp, y = (-1).dp),
                                            backgroundColor = Theme.colorScheme.red
                                        )
                                    }
                                }
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.title),
                                    color = tintColor,
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
                            unselectedContentColor = Theme.colorScheme.pureGray
                        )
                    }
                }
            },
            backgroundColor = Theme.colorScheme.white,
            contentColor = Theme.colorScheme.darkGray
        ) { paddingValues ->

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
            )
        }
    }
}
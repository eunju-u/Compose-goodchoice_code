package com.example.goodchoice.ui.main.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goodchoice.ui.home.HomeContent
import com.example.goodchoice.ui.like.LikeContent
import com.example.goodchoice.ui.main.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    viewModel: MainViewModel,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavItem.Home.route) {
            HomeContent(modifier, viewModel)
        }
        composable(NavItem.Search.route) {
        }
        composable(NavItem.Around.route) {
        }
        composable(NavItem.Like.route) {
            LikeContent(modifier, viewModel)
        }
        composable(NavItem.MyInfo.route) {
        }
    }
}

fun NavController.navigation(item: NavItem) {
    navigate(item.route) {
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
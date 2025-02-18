package com.example.goodchoice.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.common.MainBottomSheetType
import com.example.goodchoice.ui.around.AroundContent
import com.example.goodchoice.ui.around.AroundViewModel
import com.example.goodchoice.ui.home.HomeContent
import com.example.goodchoice.ui.home.HomeViewModel
import com.example.goodchoice.ui.like.LikeContent
import com.example.goodchoice.ui.like.LikeViewModel
import com.example.my_info.ui.MyInfoContent
import com.example.my_info.ui.MyInfoViewModel
import com.example.goodchoice.ui.search.SearchContent
import com.example.goodchoice.ui.search.SearchViewModel

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    aroundViewModel: AroundViewModel,
    likeViewModel: LikeViewModel,
    myInfoViewModel: com.example.my_info.ui.MyInfoViewModel,
    showBottomSheet: (type: MainBottomSheetType) -> Unit = {},
    showFilter: ()  -> Unit = {},
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavItem.Home.route) {
            HomeContent(modifier, homeViewModel)
        }
        composable(NavItem.Search.route) {
            SearchContent(modifier, searchViewModel)
        }
        composable(NavItem.Around.route) {
            AroundContent(modifier, aroundViewModel, showFilter)
        }
        composable(NavItem.Like.route) {
            LikeContent(modifier, likeViewModel)
        }
        composable(NavItem.MyInfo.route) {
            com.example.my_info.ui.MyInfoContent(modifier, myInfoViewModel, showBottomSheet)
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
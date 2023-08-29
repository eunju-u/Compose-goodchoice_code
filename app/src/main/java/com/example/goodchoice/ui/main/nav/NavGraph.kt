package com.example.goodchoice.ui.main.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goodchoice.Const
import com.example.goodchoice.R
import com.example.goodchoice.data.BannerData
import com.example.goodchoice.data.CategoryData
import com.example.goodchoice.data.CategoryItem
import com.example.goodchoice.data.HomeData
import com.example.goodchoice.ui.home.HomeContent

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavItem.Home.route) {
            val homeData = HomeData(
                categoryList = listOf(
                    CategoryData(
                        Const.KOREA,
                        listOf(
                            CategoryItem(0, "프리미엄블랙", R.drawable.ic_airplane),
                            CategoryItem(1, "모텔", R.drawable.ic_airplane),
                            CategoryItem(2, "호텔*리조트", R.drawable.ic_airplane),
                            CategoryItem(3, "펜션*풀빌라", R.drawable.ic_airplane),
                            CategoryItem(4, "홈&빌라", R.drawable.ic_airplane),
                            CategoryItem(5, "캠핑*글램핑", R.drawable.ic_airplane),
                            CategoryItem(6, "게하*한옥", R.drawable.ic_airplane),
                            CategoryItem(7, "공간대여", R.drawable.ic_airplane),
                            CategoryItem(8, "국내 항공", R.drawable.ic_airplane),
                            CategoryItem(9, "렌터카", R.drawable.ic_airplane),
                            CategoryItem(10, "레저*티켓", R.drawable.ic_airplane),
                            CategoryItem(11, "맛집", R.drawable.ic_airplane)
                        )
                    ),
                    CategoryData(
                        Const.OVERSEA,
                        listOf(
                            CategoryItem(0, "해외 항공", R.drawable.ic_airplane),
                            CategoryItem(1, "해외 숙소", R.drawable.ic_airplane),
                            CategoryItem(2, "항공+숙소", R.drawable.ic_airplane)
                        )
                    )
                ),
                bannerList = listOf(
                    BannerData(R.drawable.shape_purple),
                    BannerData(R.drawable.shape_yellow),
                    BannerData(R.drawable.shape_teal)
                )
            )
            HomeContent(modifier, homeData)
        }
        composable(NavItem.Search.route) {
        }
        composable(NavItem.Like.route) {
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
package com.example.goodchoice.ui.main.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.goodchoice.Const
import com.example.goodchoice.R

sealed class NavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val idx: Int,
    val route: String
) {
    object Home : NavItem(
        title = R.string.navi_home,
        icon = R.drawable.ic_nav_home,
        route = Const.HOME,
        idx = 1
    )

    object Search : NavItem(
        title = R.string.navi_search,
        icon = R.drawable.ic_nav_search,
        route = Const.SEARCH,
        idx = 2
    )

    object Like : NavItem(
        title = R.string.navi_like,
        icon = R.drawable.ic_nav_search,
        route = Const.SEARCH,
        idx = 3
    )

    object MyInfo : NavItem(
        title = R.string.navi_my_Info,
        icon = R.drawable.ic_nav_my_page,
        route = Const.MY_INFO,
        idx = 4
    )
}

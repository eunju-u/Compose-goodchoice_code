package com.example.goodchoice.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.common.Const
import com.example.ui_common.R

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

    object Around : NavItem(
        title = R.string.navi_around,
        icon = R.drawable.ic_around,
        route = Const.AROUND,
        idx = 3
    )


    object Like : NavItem(
        title = R.string.navi_like,
        icon = R.drawable.ic_nav_like,
        route = Const.LIKE,
        idx = 4
    )

    object MyInfo : NavItem(
        title = R.string.navi_my_Info,
        icon = R.drawable.ic_nav_my_page,
        route = Const.MY_INFO,
        idx = 5
    )
}

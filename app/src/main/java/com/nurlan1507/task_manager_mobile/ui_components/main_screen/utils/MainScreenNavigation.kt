package com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils

import com.nurlan1507.task_manager_mobile.R

enum class ProfileBottomRoutes{
    TODAY_ROUTE,
    UPCOMING_ROUTE,
    FILTER_ROUTE
}
sealed class MainScreenNavigationOption(val route:ProfileBottomRoutes, val title:String,val icon:Int) {
    object TodayTasks:
        MainScreenNavigationOption(route =ProfileBottomRoutes.TODAY_ROUTE , icon = R.drawable.today_icon, title = "Сегоднящние")
    object UpcomingTasks:
        MainScreenNavigationOption(route = ProfileBottomRoutes.UPCOMING_ROUTE, icon = R.drawable.upcoming_icon, title = "Предстоящие задания")
    object Filter:
        MainScreenNavigationOption(route = ProfileBottomRoutes.FILTER_ROUTE,icon =  R.drawable.filters_icon, title = "Фильтры и метки")
}

val MainScreenNavigationOptions = listOf(
    MainScreenNavigationOption.TodayTasks,
    MainScreenNavigationOption.UpcomingTasks,
    MainScreenNavigationOption.Filter
)





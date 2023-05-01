package com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils

import com.nurlan1507.task_manager_mobile.R

sealed class MainScreenNavigationOption(val route:String, val title:String,val icon:Int) {
    object TodayTasks:
        MainScreenNavigationOption(route = "today", icon = R.drawable.today_icon, title = "Входящие")
    object UpcomingTasks:
        MainScreenNavigationOption(route = "upcoming", icon = R.drawable.upcoming_icon, title = "Предстоящие задания")
    object IncomingTasks:
        MainScreenNavigationOption(route = "incoming",icon =  R.drawable.incoming_icon, title = "Входящие задания")
    object Filter:
        MainScreenNavigationOption(route = "Filter",icon =  R.drawable.filters_icon, title = "Фильтры и метки")
}

val MainScreenNavigationOptions = listOf(
    MainScreenNavigationOption.IncomingTasks,
    MainScreenNavigationOption.TodayTasks,
    MainScreenNavigationOption.UpcomingTasks,
    MainScreenNavigationOption.Filter
)


data class MainScreenDynamicNavigationOption(
    val navOption: MainScreenNavigationOption,
    val dynamicField: Int
)


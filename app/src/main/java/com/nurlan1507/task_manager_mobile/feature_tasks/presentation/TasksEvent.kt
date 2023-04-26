package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenNavigationOption

sealed class TasksEvent {
    data class ChangeCategory(val category:MainScreenNavigationOption):TasksEvent()

    data class EnteredTitle(val value:String):TasksEvent()
    data class EnteredDescription(val value:String):TasksEvent()
    data class EnteredFinishDate(val value:String):TasksEvent()

    data class EnteredProjectId(val value:String):TasksEvent()
}
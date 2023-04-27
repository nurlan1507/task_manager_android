package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenNavigationOption

sealed class TasksEvent {
    data class ChangeCategory(val category:MainScreenNavigationOption):TasksEvent()

    data class EnteredTitle(val value:String):TasksEvent()
    data class EnteredDescription(val value:String):TasksEvent()
    data class EnteredFinishDate(val value:Long?):TasksEvent()

    data class EnteredProjectId(val value:String):TasksEvent()
    class ClearTextFieldState():TasksEvent()
    data class ChangeBottomSheetDestination(val type: BottomSheetLayoutType?):TasksEvent()
}
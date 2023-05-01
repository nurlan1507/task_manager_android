package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.DateSelectionMenu
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption

sealed class TasksEvent {
    data class ChangeCategory(val category: MainScreenNavigationOption):TasksEvent()

    data class EnteredTitle(val value:String):TasksEvent()
    data class EnteredDescription(val value:String):TasksEvent()
    data class EnteredFinishDate(val value:Long?):TasksEvent()

    data class EnteredProjectId(val value:String):TasksEvent()

    object  CreateTask:TasksEvent()
    data class CreateProject(val project: Project):TasksEvent()
    object ClearTextFieldState : TasksEvent()
    data class ChangeBottomSheetDestination(val type: BottomSheetLayoutType?):TasksEvent()

    data class ChangeDateSelectionOption(val type: DateSelectionMenu):TasksEvent()
    object ValidateTextFields : TasksEvent()

    data class GetProjectById(val id:String):TasksEvent()

}
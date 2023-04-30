package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenNavigationOption

data class TasksState (
    val currentCategory:MainScreenNavigationOption = MainScreenNavigationOption.TodayTasks,
    val currentProject:ProjectWithTasks? = null,
    val projects:List<ProjectWithTasks> = listOf(),

    val task: Task = Task(0)

        )

enum class BottomSheetAction {
    INITIAL,  // initial case before user action
    HIDE,     // user wants to hide bottom sheet
    SHOW,     // user wants to show bottom sheet
    TIMED     // user wants to see and hide bottom sheet for some time, and disables screen interaction
}

sealed class BottomSheetContent {

    // ERROR type content data
    data class ErrorSheetContent(val message: String) : BottomSheetContent()

    // REDIRECT type content data
    data class RedirectSheetContent(val message: String, val redirect: String) : BottomSheetContent()

    // LIST type content data
    data class ListSheetContent(val title: String, val data: List<String>) : BottomSheetContent()

    // INITAL type content data
    object InitialSheetContent : BottomSheetContent()
}
data class ModalBottomSheetState(
    val bottomSheetContent: BottomSheetContent = BottomSheetContent.InitialSheetContent,
    val bottomSheetType: BottomSheetLayoutType = BottomSheetLayoutType.AddTask,
    val bottomSheetActionType: BottomSheetAction = BottomSheetAction.INITIAL
)
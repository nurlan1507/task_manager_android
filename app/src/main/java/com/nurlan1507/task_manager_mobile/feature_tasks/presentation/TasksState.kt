package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.DateSelectionMenu
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption

data class TasksState (
    val currentCategory: MainScreenNavigationOption = MainScreenNavigationOption.TodayTasks,
    val dateSelectionOption: DateSelectionMenu? = null,
    val currentProject:ProjectWithTasks? = null,
    val projects:List<Project> = listOf(Project(projectId = "11", title = "Входящие"), Project(projectId = "22", title = "Работа")),
    val task: Task = Task(0)

)
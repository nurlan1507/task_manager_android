package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenNavigationOption

data class TasksState (
    val currentCategory:MainScreenNavigationOption = MainScreenNavigationOption.TodayTasks,
    val currentProject:ProjectWithTasks? = null,
    val projects:List<ProjectWithTasks> = listOf(),

    val task: Task = Task()

        )
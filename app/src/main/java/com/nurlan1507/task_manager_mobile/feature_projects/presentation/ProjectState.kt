package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.ProfileBottomRoutes

data class ProjectState(
    val currentProject:ProjectWithTasks?= null,
    val projectList:List<Project>?=null,
    val currentCategory:MainScreenNavigationOption = MainScreenNavigationOption.TodayTasks
)
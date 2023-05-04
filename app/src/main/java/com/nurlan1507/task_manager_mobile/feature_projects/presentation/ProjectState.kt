package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks

data class ProjectState(
    val currentProject:ProjectWithTasks?= null,
    val projectList:List<Project>?=null
)
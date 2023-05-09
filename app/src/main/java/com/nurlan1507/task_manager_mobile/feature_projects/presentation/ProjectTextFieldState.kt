package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectColor


data class ProjectTextFieldState(
    val title: String = "",
    val color: ProjectColor =  ProjectColor(0xFFFF1744, "Красный"),
)
package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.utils.TokenManager

sealed class ProjectEvent {
    data class getProject(val projectId:Int):ProjectEvent()
    data class CreateProject(val project: Project):ProjectEvent()

    data class GetProjectsNetwork(val token:String?= TokenManager.getAccessToken()):ProjectEvent()
    data class GetProjects(val userId:String? = TokenManager.getUserId()):ProjectEvent()
}
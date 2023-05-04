package com.nurlan1507.task_manager_mobile.feature_projects.api

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasksR
import com.nurlan1507.task_manager_mobile.feature_users.api.UserAuthJson

class ProjectApiModel {
}

data class ProjectApiResponse(
    val code:Int,
    val message:String,
    val data: ProjectWithTasks
)

data class ProjectArrApiResponse(
    val code:Int,
    val message:String,
    val projects: List<ProjectWithTasksR>
)
package com.nurlan1507.task_manager_mobile.feature_projects.domain.repository

import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectArrApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.restService.NetworkResult

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun getProject(projectId:String): ProjectWithTasks

    suspend fun createProject(project: Project)
    suspend fun getProjectNetwork(id:Int): NetworkResult<ProjectApiResponse>
    suspend fun getProjectsNetwork():NetworkResult<ProjectArrApiResponse>

    suspend fun createProjectNetwork(project: Project):NetworkResult<ProjectApiResponse>

}
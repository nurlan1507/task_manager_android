package com.nurlan1507.task_manager_mobile.feature_projects.api

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthService
import com.nurlan1507.task_manager_mobile.feature_users.api.GoogleSignInRequestBody

class ProjectRemoteDataSource(private val projectService: ProjectService) {
    suspend fun createProject(project: Project, token: String) = projectService.createProject(project = project, accessToken = token)

    suspend fun getProjectById(id:Int,token:String) = projectService.getProjectById(id=id, accessToken = token)

    suspend fun getProjects(token:String) = projectService.getProjects(accessToken = token)

}
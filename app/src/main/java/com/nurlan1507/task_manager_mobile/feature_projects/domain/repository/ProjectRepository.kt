package com.nurlan1507.task_manager_mobile.feature_projects.domain.repository

import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectArrApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.restService.NetworkResult

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun getProject(projectId:Int): Project

    suspend fun createProject(project: Project):Long
    suspend fun getProjectNetwork(id:Int): NetworkResult<ProjectApiResponse>
//    suspend fun getProjectWithTask(projectId:Int):ProjectWithTasks
    suspend fun getProjects():List<Project>

    suspend fun getProjectsWithTasksDueToday(today:Long):List<ProjectWithTasks>
    suspend fun getProjectsNetwork():NetworkResult<ProjectArrApiResponse>

    suspend fun insertProjectWithTasks(project:Project, tasks:List<Task>)
    suspend fun createProjectNetwork(project: Project):NetworkResult<ProjectApiResponse>

}
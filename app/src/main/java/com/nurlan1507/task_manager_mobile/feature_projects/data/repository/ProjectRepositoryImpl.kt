package com.nurlan1507.task_manager_mobile.feature_projects.data.repository

import android.util.Log
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectArrApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_projects.data.ProjectDao
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.restService.BaseApiResponse
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.utils.TokenManager

class ProjectRepositoryImpl(val projectDao:ProjectDao, val projectRemoteDataSource:ProjectRemoteDataSource):ProjectRepository, BaseApiResponse() {
    override suspend fun insertProject(project: Project) {
        projectDao.createProject(project)
    }

    override suspend fun getProject(projectId: Int): Project {
        return projectDao.getProject(projectId = projectId)
    }

    override suspend fun createProject(project: Project):Long {
        return projectDao.createProject(project)
    }

    override suspend fun getProjectNetwork(id:Int): NetworkResult<ProjectApiResponse> {
        return saveApiCall {
            projectRemoteDataSource.getProjectById(id,TokenManager.getAccessToken().toString())
        }
    }

//    override suspend fun getProjectWithTask(projecId: Int): ProjectWithTasks {
//        return projectDao.getProject(projectId = projecId)
//    }

    override suspend fun getProjects(): List<Project> {
        return projectDao.getProjects(TokenManager.getUserId().toString())
    }

    override suspend fun getProjectsWithTasksDueToday(today:Long): List<ProjectWithTasks> {
        return projectDao.getTodayDueTasks()
    }

    override suspend fun getProjectsNetwork(): NetworkResult<ProjectArrApiResponse> {
        return saveApiCall {
            Log.d("getProjects",TokenManager.getAccessToken().toString())
            projectRemoteDataSource.getProjects(TokenManager.getAccessToken().toString())
        }
    }

    override suspend fun insertProjectWithTasks(project: Project, tasks: List<Task>) {
        projectDao.insertProjectWithTasks(project, tasks)
//        projectDao.createProject(project)
    }

    override suspend fun createProjectNetwork(project: Project): NetworkResult<ProjectApiResponse> {
        return saveApiCall {
            projectRemoteDataSource.createProject(project, TokenManager.getAccessToken().toString())
        }
    }


}
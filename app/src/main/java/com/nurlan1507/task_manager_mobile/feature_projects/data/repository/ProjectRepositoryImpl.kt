package com.nurlan1507.task_manager_mobile.feature_projects.data.repository

import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_projects.data.ProjectDao
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository

class ProjectRepositoryImpl(val projectDao:ProjectDao, val projectRemoteDataSource:ProjectRemoteDataSource):ProjectRepository {
    override suspend fun insertProject(project: Project) {
        projectDao.createProject(project)
    }

    override suspend fun getProject(projectId: String): ProjectWithTasks {
        return projectDao.getProject(projectId = projectId)
    }

}
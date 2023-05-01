package com.nurlan1507.task_manager_mobile.feature_projects.domain.repository

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun getProject(projectId:String): ProjectWithTasks
}
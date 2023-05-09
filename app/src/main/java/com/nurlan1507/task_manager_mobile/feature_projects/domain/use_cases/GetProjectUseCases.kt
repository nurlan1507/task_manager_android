package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository

class GetProjectUseCase(private val repository:ProjectRepository) {
    suspend operator fun invoke(projectId:Int): Project {
        return repository.getProject(projectId)
    }
}
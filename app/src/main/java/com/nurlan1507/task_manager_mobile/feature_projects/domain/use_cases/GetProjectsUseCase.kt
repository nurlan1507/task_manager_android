package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository

class GetProjectsUseCase(private val repository:ProjectRepository) {

    suspend operator fun invoke(): List<Project> {
        return repository.getProjects()

    }
}
package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.data.repository.ProjectRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.restService.NetworkResult

class CreateProjectNetworkUseCase (private val repository:ProjectRepositoryImpl){
    suspend operator fun invoke(project:Project): NetworkResult<ProjectApiResponse> {
        val result = repository.createProjectNetwork(project = project)
        return result
    }
}
package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.utils.TokenManager

class GetTasksNetworkUseCase(private val repositoryImpl: TasksRepositoryImpl) {
    suspend operator fun invoke(projectId:Int): NetworkResult<List<Task>> {
        val accessToken = TokenManager.getAccessToken().toString()
        val networkCall = repositoryImpl.getTasksNetwork(projectId = projectId, token = accessToken)
        return networkCall
    } 
}
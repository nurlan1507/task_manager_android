package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.utils.TokenManager

class CreateTaskNetworkUseCase(private val repositoryImpl: TasksRepositoryImpl) {
    suspend operator fun invoke(task: Task): NetworkResult<Task> {
        val accessToken = TokenManager.getAccessToken().toString()
        val networkCall = repositoryImpl.createTaskNetwork(task = task, token = accessToken)
        return networkCall
    }
}
package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val repository:TasksRepositoryImpl) {
    suspend operator fun invoke(projectId:String): List<Task> {
        return repository.getTasks(projectId = projectId)
    }
}
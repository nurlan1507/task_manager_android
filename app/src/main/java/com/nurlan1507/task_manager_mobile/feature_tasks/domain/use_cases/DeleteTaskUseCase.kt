package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task

class DeleteTaskUseCase(private val repository: TasksRepositoryImpl) {
    suspend operator fun invoke(task: Task){
        repository.deleteTask(task)
    }
}
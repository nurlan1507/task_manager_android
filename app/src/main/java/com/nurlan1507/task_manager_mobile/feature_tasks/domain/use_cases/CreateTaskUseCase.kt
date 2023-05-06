package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry.TasksRepository

class CreateTaskUseCase(private val repository:TasksRepositoryImpl) {
    suspend operator fun invoke(task: Task):Task{
        val newTaskId =  repository.insertTask(task)
        val newTask = repository.getTask(newTaskId)
        return newTask
    }

}
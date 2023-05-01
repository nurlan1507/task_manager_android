package com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task

interface TasksRepository {
    suspend fun insertTask(task: Task):Long

    suspend fun getTasks(projectId:String): List<Task>

}
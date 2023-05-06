package com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject

interface TasksRepository {
    suspend fun insertTask(task: Task):Long

    suspend fun getTask(taskId:Long):Task
    suspend fun getTasks(projectId:Int): List<TaskWithProject>
    suspend fun getTasksDueToday(today:Long):List<TaskWithProject>


}
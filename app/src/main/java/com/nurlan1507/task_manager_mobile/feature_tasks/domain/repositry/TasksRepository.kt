package com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.restService.NetworkResult

interface TasksRepository {
    suspend fun insertTask(task: Task):Long

    suspend fun getTask(taskId:Long):Task
    suspend fun getTasks(projectId:Int): List<TaskWithProject>
    suspend fun getTasksDueToday(today:Long):List<TaskWithProject>

    suspend fun getTasksNetwork(projectId:Int,token:String):NetworkResult<List<Task>>

    suspend fun createTaskNetwork(task:Task, token:String): NetworkResult<Task>
    suspend fun deleteTask(task:Task)
}
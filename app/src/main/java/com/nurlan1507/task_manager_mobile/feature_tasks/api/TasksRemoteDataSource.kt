package com.nurlan1507.task_manager_mobile.feature_tasks.api

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task

class TasksRemoteDataSource(private val tasksService: TasksService) {

    suspend fun getTasks(projectId:Int, token:String) = tasksService.getTasks(id = projectId, token = token)

    suspend fun createTask(task:Task, token:String) = tasksService.createTask(task = task,token = token)


}
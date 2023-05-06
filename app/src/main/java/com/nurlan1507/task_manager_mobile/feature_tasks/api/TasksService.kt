package com.nurlan1507.task_manager_mobile.feature_tasks.api

import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TasksService {

    @POST("/api/tasks/createTask")
    suspend fun createTask(
        task:Task,
        @Header("Authorization")token:String
    ): Response<Task>

    @GET("/api/tasks/getTasks/{id}")
    suspend fun getTasks(
        id:Int,
        @Header("Authorization")token:String
    ): Response<List<Task>>
}
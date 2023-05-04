package com.nurlan1507.task_manager_mobile.feature_projects.api

import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {

    @POST("/api/projects/createProject")
    suspend fun createProject(@Body project:Project, @Header("Authorization")accessToken: String):Response<ProjectApiResponse>

    @GET("/api/projects/getProject/{id}")
    suspend fun getProjectById(
        @Path("id") id:Int,
        @Header("Authorization") accessToken:String
    ):Response<ProjectApiResponse>

    @GET("/api/projects/getProjects")
    suspend fun getProjects(
        @Header("Authorization") accessToken:String
    ):Response<ProjectArrApiResponse>
}
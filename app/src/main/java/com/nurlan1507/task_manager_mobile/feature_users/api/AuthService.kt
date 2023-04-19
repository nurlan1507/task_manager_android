package com.nurlan1507.task_manager_mobile.feature_users.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("api/googleSignIn")
    suspend fun googleSignIn(@Body body: GoogleSignInRequestBody): Response<AuthApiResponse>

}
package com.nurlan1507.task_manager_mobile.feature_users.api

import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User

data class AuthApiResponse(
    val code:Int,
    val message:String,
    val data:User
)

data class UserAuthJson(
    val id:String,
    val email:String,
    val username:String,
    val accessToken:String,
    val refreshToken:String
)

data class GoogleSignInRequestBody(
    val id_token:String,
    val username:String,
    val email:String
)
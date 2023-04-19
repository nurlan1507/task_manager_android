package com.nurlan1507.task_manager_mobile.feature_users.api

data class AuthApiResponse(
    val code:Int,
    val message:String
)

data class GoogleSignInRequestBody(
    val id_token:String
)
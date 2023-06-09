package com.nurlan1507.task_manager_mobile.feature_users.api

class AuthRemoteDataSource(private val authService: AuthService) {
    suspend fun googleSignIn(
        token_id:String,
        username:String,
        email:String
    )=authService.googleSignIn(GoogleSignInRequestBody(id_token = token_id, username = username, email = email))

    suspend fun facebookSignIn(
        token_id: String
    ) = 1 + 2
}
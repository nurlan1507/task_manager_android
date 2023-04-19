package com.nurlan1507.task_manager_mobile.feature_users.domain.repository

import com.nurlan1507.task_manager_mobile.feature_users.api.AuthApiResponse
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {
    fun getUser(userId:String): User?

    fun getFriends(userId:String):Flow<List<User>>

    suspend fun addUser(user: User)


    suspend fun googleSignIn(google_id_token:String):NetworkResult<AuthApiResponse>
}
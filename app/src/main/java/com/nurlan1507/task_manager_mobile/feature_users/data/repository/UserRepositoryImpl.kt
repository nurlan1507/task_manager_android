package com.nurlan1507.task_manager_mobile.feature_users.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthApiResponse
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthService
import com.nurlan1507.task_manager_mobile.feature_users.api.GoogleSignInRequestBody
import com.nurlan1507.task_manager_mobile.feature_users.api.UserAuthJson
import com.nurlan1507.task_manager_mobile.feature_users.data.UserDao
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.restService.BaseApiResponse
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(private val dao:UserDao, private val remoteDataSource: AuthRemoteDataSource): UserRepository, BaseApiResponse() {
    override fun getUser(userId:String): User? {
        return dao.getUser(userId)
    }

    override fun getFriends(userId: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun googleSignIn(google_id_token:String,username:String, email:String): NetworkResult<AuthApiResponse> {
        Log.d("googleAuth","processing auth")
        return saveApiCall {
            remoteDataSource.googleSignIn(google_id_token,username,email)
        }
    }
}
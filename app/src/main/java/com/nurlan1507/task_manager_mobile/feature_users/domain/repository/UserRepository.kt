package com.nurlan1507.task_manager_mobile.feature_users.domain.repository

import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId:String): User?

    fun getFriends(userId:String):Flow<List<User>>

    suspend fun addUser(user: User)

}
package com.nurlan1507.task_manager_mobile.feature_users.data.repository

import com.nurlan1507.task_manager_mobile.feature_users.data.UserDao
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(private val dao: UserDao): UserRepository {
    override fun getUser(userId:String): User? {
        return dao.getUser(userId)
    }

    override fun getFriends(userId: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(user: User) {
//        dao.insertUser(user)
    }
}
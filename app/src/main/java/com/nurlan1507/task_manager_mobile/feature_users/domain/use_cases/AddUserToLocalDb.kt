package com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository

class AddUserToLocalDb(private val repository: UserRepository) {
    suspend operator fun invoke(user: User){
        repository.addUser(user)
    }
}
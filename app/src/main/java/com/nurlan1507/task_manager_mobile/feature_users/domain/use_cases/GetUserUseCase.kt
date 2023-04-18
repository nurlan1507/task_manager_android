package com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases

import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId:String): User?{
        return userRepository.getUser(userId)
    }
}
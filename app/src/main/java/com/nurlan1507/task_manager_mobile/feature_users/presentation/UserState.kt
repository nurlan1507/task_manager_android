package com.nurlan1507.task_manager_mobile.feature_users.presentation

import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User

data class UserState(
    val apiCallResult:Int = 0,
    val currentUser: User?=null
)
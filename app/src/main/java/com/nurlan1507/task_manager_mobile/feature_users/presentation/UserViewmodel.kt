package com.nurlan1507.task_manager_mobile.feature_users.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.UserUseCases
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase


class UserViewModel(application: Application):AndroidViewModel(application){
    private val repository: UserRepository
    init{
        val userDao = TaskManagerDatabase.getDatabase(application).userDao()
        repository = UserRepositoryImpl(userDao,AuthRemoteDataSource(RestService.authService))
    }

}
package com.nurlan1507.task_manager_mobile.feature_users.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GetUserUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GoogleSignInUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.UserUseCases
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import kotlinx.coroutines.launch


class UserViewModel(application: Application):AndroidViewModel(application){
    private val repository: UserRepository
    private val userUseCases:UserUseCases

    var resultCode = mutableStateOf(0)
    init{
        val userDao = TaskManagerDatabase.getDatabase(application).userDao()
        repository = UserRepositoryImpl(userDao,AuthRemoteDataSource(RestService.authService))
        userUseCases = UserUseCases(GetUserUseCase(repository),GoogleSignInUseCase(repository))
    }

    fun onEvent(event:UserEvent){
        when(event){
            is UserEvent.GoogleSignInEvent ->{
                viewModelScope.launch {
                    var result = userUseCases.googleSignInUseCase(event.id,event.username,event.email)
                    resultCode.value = result.code
                }
            }

            else ->{}
        }
    }
}
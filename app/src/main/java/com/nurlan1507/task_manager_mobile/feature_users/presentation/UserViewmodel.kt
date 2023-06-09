package com.nurlan1507.task_manager_mobile.feature_users.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.AddUserToLocalDb
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GetUserUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GoogleSignInUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.UserUseCases
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import com.nurlan1507.task_manager_mobile.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    val repository: UserRepositoryImpl,
    val userUseCases: UserUseCases
):ViewModel(){

    private val _state = mutableStateOf<UserState>(UserState())
    val state: State<UserState> = _state
    var resultCode = MutableStateFlow(0)

    fun onEvent(event:UserEvent){
        when(event){
            is UserEvent.GoogleSignInEvent ->{
                viewModelScope.launch {
                    val result = userUseCases.googleSignInUseCase(event.id,event.username,event.email)
                    _state.value = _state.value.copy(apiCallResult = result.code)
                    resultCode.value = result.code
                    if(resultCode.value == 200){
                        val newUser = User(userId = event.id, username = event.username, email = event.email)
                        userUseCases.addUserLocal(newUser)
                        _state.value = _state.value.copy(currentUser = newUser)

                    }
                }
            }
            else ->{}
        }
    }
}
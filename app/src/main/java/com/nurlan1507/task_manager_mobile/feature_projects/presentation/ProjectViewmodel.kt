package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_projects.data.repository.ProjectRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.CreateProjectNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.CreateProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectsNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectsUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetTodaysProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.ProjectUseCases
import com.nurlan1507.task_manager_mobile.restService.ResponseModel
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.ProfileBottomRoutes
import com.nurlan1507.task_manager_mobile.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class ProjectViewmodel @Inject constructor(
    val repository: ProjectRepositoryImpl,
    val projectUseCases: ProjectUseCases
):ViewModel(){
    private var _projectState = mutableStateOf<ProjectState>(ProjectState())
    val projectState: State<ProjectState> = _projectState

    private var _textFieldState = mutableStateOf<ProjectTextFieldState>(ProjectTextFieldState())
    val textFieldState:State<ProjectTextFieldState> = _textFieldState



    private var _error = mutableStateOf<ResponseModel>(ResponseModel())
    val error:State<ResponseModel> = _error

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event:ProjectEvent){
        when(event){
            is ProjectEvent.getProject -> {
                viewModelScope.launch {
                    val project = repository.getProject(event.projectId)
                    Log.d("projectWithTasks", project.toString())
                    _projectState.value = _projectState.value.copy(currentProject = project)
                    when(_projectState.value.currentCategory.route){
                        ProfileBottomRoutes.TODAY_ROUTE ->{
                            projectUseCases.getTodayTaskUseCase()
                        }
                        else ->{

                        }
                    }
                }
            }
            is ProjectEvent.CreateProject -> {
                viewModelScope.launch {
                    repository.insertProject(event.project)
                }
            }
            is ProjectEvent.GetProjectsNetwork ->{
                viewModelScope.launch {
                    val projects = projectUseCases.getProjectsNetworkUseCase()

                }
            }
            is ProjectEvent.GetProjects ->{
                viewModelScope.launch {
                    val projects =  projectUseCases.getProjectsUseCase()
                    _projectState.value = _projectState.value.copy(projectList = projects, currentProject = projects[0])
                }
            }
            is ProjectEvent.ChangeCategory->{

            }
            is ProjectEvent.ProjecTitleEntered ->{
                _textFieldState.value = _textFieldState.value.copy(title = event.title)
            }
            is ProjectEvent.ProjectColorSelected ->{
                _textFieldState.value = _textFieldState.value.copy(color = event.color)
            }
            is ProjectEvent.CreateProjectNetwork ->{
                viewModelScope.launch {
                    val userId = TokenManager.getUserId()
                    val newProject = Project(title = _textFieldState.value.title, iconUrl = "", userId = userId!! )
                    val result = projectUseCases.createProjectNetworkUseCase(newProject)
                    if(result.code!=200){
                        _error.value = _error.value.copy(code = result.code, message = result.data?.message)
                        return@launch
                    }
                    projectUseCases.createProjectUseCase(newProject)
                    _projectState.value = _projectState.value.copy(currentProject =newProject )
                }
            }
        }
    }
}
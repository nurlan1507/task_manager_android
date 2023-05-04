package com.nurlan1507.task_manager_mobile.feature_projects.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_projects.data.repository.ProjectRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.CreateProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.ProjectUseCases
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectsUseCase
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import kotlinx.coroutines.launch

class ProjectViewmodel(application: Application):AndroidViewModel(application) {
    private var _projectState = mutableStateOf<ProjectState>(ProjectState())
    val projectState: State<ProjectState> = _projectState
    val projectUseCases: ProjectUseCases
    private val repository: ProjectRepositoryImpl
    init{
        val projectDao = TaskManagerDatabase.getDatabase(application).projectDao()
        repository = ProjectRepositoryImpl(projectDao = projectDao ,ProjectRemoteDataSource(RestService.projectService))
        projectUseCases = ProjectUseCases(GetProjectUseCase(repository),CreateProjectUseCase(repository), GetProjectsUseCase(repository))
        viewModelScope.launch {
            try{
            }catch (e:Exception){
            }
        }
    }
    fun onEvent(event:ProjectEvent){
        when(event){
            is ProjectEvent.getProject -> {
                viewModelScope.launch {
                    val project = repository.getProject(event.projectId)
                    Log.d("projectWithTasks", project.toString())
                    _projectState.value = _projectState.value.copy(currentProject = project)
                }
            }
            is ProjectEvent.CreateProject -> {
                viewModelScope.launch {
                    repository.insertProject(event.project)
                }
            }
            is ProjectEvent.GetProjects ->{
                viewModelScope.launch {
                    projectUseCases.getProjectsUseCase()
                }
            }
        }
    }
}
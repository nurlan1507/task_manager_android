package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.CreateTaskNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.CreateTaskUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.DeleteTaskUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetDueTodayTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetTasksNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetTasksUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.TasksUseCases
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.restService.ResponseModel
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOptions
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.ProfileBottomRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

class TasksViewModel(application: Application):AndroidViewModel(application) {
    private val repository: TasksRepositoryImpl
    private var useCases:TasksUseCases

    private val _tasksState = mutableStateOf(TasksState())
    val tasksState: State<TasksState> = _tasksState

    private var _fieldState = mutableStateOf(TasksTextFieldState())
    var fieldState:State<TasksTextFieldState> = _fieldState

    private var _currentBottomSheetLayout = mutableStateOf<BottomSheetLayoutType?>(null)
    val currentBottomSheetLayout:State<BottomSheetLayoutType?> = _currentBottomSheetLayout

    private var _error = mutableStateOf(false)
    var error: MutableState<Boolean> = _error

    private var _callResult = mutableStateOf(ResponseModel())
    val callResult:State<ResponseModel> = _callResult
    init{
        val taskDao = TaskManagerDatabase.getDatabase(application).taskDao()
        repository = TasksRepositoryImpl(taskDao =taskDao , TasksRemoteDataSource(RestService.tasksService))
        useCases = TasksUseCases(
            CreateTaskUseCase(repository),
            GetTasksUseCase(repository),
            GetDueTodayTasks(repository),
            DeleteTaskUseCase(repository),
            GetTasksNetworkUseCase(repository),
            CreateTaskNetworkUseCase(repository),
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNextWeekendDays(): List<LocalDate> {
        val today = fieldState.value.finishDate.let {
            if(it!=null) Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate()
            else LocalDate.now()
        }
        val nextSaturday = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
        val nextSunday = today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
        if(today.dayOfMonth > nextSaturday.dayOfMonth || today.dayOfMonth > nextSunday.dayOfMonth){
            val followingSaturday = nextSaturday.plusWeeks(1)
            val followingSunday = nextSunday.plusWeeks(1)
            return listOf(followingSaturday,followingSunday)
        }
        return listOf(nextSaturday, nextSunday)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.ChangeCategory ->{
                viewModelScope.launch {
                    _tasksState.value = _tasksState.value.copy(currentCategory = event.category)
                }
            }
            is TasksEvent.EnteredTitle ->{
                _fieldState.value = _fieldState.value.copy(title = event.value)
            }
            is TasksEvent.EnteredDescription->{
                _fieldState.value = _fieldState.value.copy(description = event.value)
            }
            is TasksEvent.EnteredFinishDate->{
                _fieldState.value = _fieldState.value.copy(finishDate = event.value)
            }
            is TasksEvent.EnteredProjectId->{
                _fieldState.value = _fieldState.value.copy(projectId = event.value)
            }
            is TasksEvent.ClearTextFieldState->{
                _fieldState.value = TasksTextFieldState()
            }
            is TasksEvent.ChangeBottomSheetDestination ->{
                _currentBottomSheetLayout.value = event.type
            }
            is TasksEvent.ValidateTextFields -> {
                if(_fieldState.value.title.isNotEmpty()){
                    _error.value
                }
            }
            is TasksEvent.ChangeDateSelectionOption ->{
                _tasksState.value = _tasksState.value.copy(dateSelectionOption = event.type)
            }
            is TasksEvent.CreateTask -> {
                viewModelScope.launch {
                    val task = Task(title = _fieldState.value.title, description = _fieldState.value.description, finishDate = _fieldState.value.finishDate, projectId = _fieldState.value.projectId)
                    val netWorkResult = useCases.createTaskNetworkUseCase(task = task)
                    if(netWorkResult.code!=200){
                        _callResult.value = _callResult.value.copy(
                            code = netWorkResult.code,
                            message = netWorkResult.message
                        )
                        return@launch
                    }
                    val newTask = useCases.createTaskUseCase(task)
                    if(_tasksState.value.tasks.isNotEmpty() && newTask.projectId == _tasksState.value.tasks[0].project.projectId){
                        val projectInfo = _tasksState.value.tasks[0].project
                        val newTaskWithProjectInstance = TaskWithProject(
                            project = Project(projectId = projectInfo.projectId, title = projectInfo.title, iconUrl = projectInfo.iconUrl, userId = projectInfo.userId),
                            task = newTask
                        )
                        val list = listOf(newTaskWithProjectInstance)
//                        _tasksState.value.tasks.toMutableList().add(newTaskWithProjectInstance)
                        _tasksState.value = _tasksState.value.copy(tasks = _tasksState.value.tasks + list)
                    }
                    _fieldState.value = TasksTextFieldState()
                }
            }
            is TasksEvent.GetTasks -> {
                viewModelScope.launch {
//                    val projectId = event.projectId
//                    when(_tasksState.value.currentCategory.route){
//                        ProfileBottomRoutes.TODAY_ROUTE ->{
//                            val tasks =  useCases.getDueTodayTasks()
//                            _tasksState.value = _tasksState.value.copy(tasks = tasks)
//                            return@launch
//                        }else ->{
//
//                        }
//                    }
                    val projectTasks = useCases.getTasksUseCase(1)
                    _tasksState.value = _tasksState.value.copy(tasks = projectTasks)
                }
            }
            is TasksEvent.DeleteTask ->{
                viewModelScope.launch {
                    useCases.deleteTaskUseCase(task = event.task)
                    _tasksState.value = _tasksState.value.copy(deletedTask = event.task, tasks = _tasksState.value.tasks.filterNot{
                            item -> item.task == event.task
                    })
                }
            }
        }
    }
}
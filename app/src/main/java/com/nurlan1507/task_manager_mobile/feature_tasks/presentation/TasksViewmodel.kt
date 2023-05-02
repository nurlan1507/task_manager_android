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
import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.CreateTaskUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetTasksUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.TasksUseCases
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
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


    init{
        val taskDao = TaskManagerDatabase.getDatabase(application).taskDao()
        repository = TasksRepositoryImpl(taskDao =taskDao , TasksRemoteDataSource())
        useCases = TasksUseCases(CreateTaskUseCase(repository) , GetTasksUseCase(repository))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNextWeekendDays(): List<LocalDate> {
        val today = LocalDate.now()
        val nextSaturday = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
        val nextSunday = today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
        if(today.dayOfWeek == DayOfWeek.SUNDAY || today.dayOfWeek == DayOfWeek.SATURDAY){
            val followingSaturday = nextSaturday.plusWeeks(1)
            val followingSunday = nextSunday.plusWeeks(1)
            return listOf(followingSaturday,followingSunday)
        }
        return listOf(nextSaturday, nextSunday)
    }


    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.ChangeCategory ->{
                _tasksState.value = _tasksState.value.copy(currentCategory = event.category)
                when(event.category){
                    is MainScreenNavigationOption.TodayTasks ->{
                        viewModelScope.launch {
                            val tasks = useCases.getTasksUseCase("11")
                            Log.d("tasksGet", tasks.toString())
                            _tasksState.value =_tasksState.value.copy(tasks = tasks)
                        }
                    }
                    else ->{}
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
                    val task = Task(title = _fieldState.value.title, description = _fieldState.value.description, finishDate = _fieldState.value.finishDate)
                    useCases.createTaskUseCase(task)
                }
            }
            is TasksEvent.CreateProject -> {
//                viewModelScope.launch{
//                    repository.
//                }
            }
        }
    }
}
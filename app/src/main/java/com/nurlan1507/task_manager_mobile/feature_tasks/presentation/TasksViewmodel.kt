package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.BottomSheetLayoutType
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.CurrentBottomSheetLayout
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserEvent
import kotlinx.coroutines.launch

class TasksViewModel(application: Application):AndroidViewModel(application) {
    private val _tasksState = mutableStateOf(TasksState())
    val tasksState: State<TasksState> = _tasksState

    private var _fieldState = mutableStateOf(TasksTextFieldState())
    var fieldState:State<TasksTextFieldState> = _fieldState

    private var _currentBottomSheetLayout = mutableStateOf<BottomSheetLayoutType?>(null)
    val currentBottomSheetLayout:State<BottomSheetLayoutType?> = _currentBottomSheetLayout
    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.ChangeCategory ->{
                _tasksState.value = _tasksState.value.copy(currentCategory = event.category)
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

        }
    }
}
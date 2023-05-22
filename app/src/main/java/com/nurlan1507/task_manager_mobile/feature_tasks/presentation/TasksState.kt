package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.DateSelectionMenu
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption

data class TasksState (
    val currentCategory: MainScreenNavigationOption = MainScreenNavigationOption.TodayTasks,
    val dateSelectionOption: DateSelectionMenu? = null,
    val currentProject: ProjectWithTasks? = null,
    val projects:MutableList<Project> = mutableListOf(Project(projectId = 1, title = "Входящие", iconUrl = R.drawable.incoming_icon.toString()), Project(projectId = 22, title = "Работа",iconUrl = R.drawable.incoming_icon.toString())),
    var tasks:List<TaskWithProject> = listOf<TaskWithProject>(),
    val categorizedTasks:Map<String,List<TaskWithProject>> = mapOf(),
    val task: Task = Task(0),
    val currentDragItem:CurrentDragItem? = null,
    val deletedTask:Task?=null,
    val isCurrentlyDragging: Boolean = false

)

data class CurrentDragItem(
    val task:TaskWithProject,
    val data:Long
)
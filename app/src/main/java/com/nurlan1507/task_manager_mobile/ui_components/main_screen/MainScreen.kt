package com.nurlan1507.task_manager_mobile.ui_components.main_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectEvent
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.DateSelectionBottomSheetLayout
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.MainBottomSheetLayout
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.TaskCreationBottomSheetLayout
import com.nurlan1507.task_manager_mobile.global_components.BottomNavigationBar
import com.nurlan1507.task_manager_mobile.global_components.TopBar
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.components.CustomAlertDialog
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.components.IncomeTaskView
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.components.TaskView
import com.nurlan1507.task_manager_mobile.utils.Screen
import com.nurlan1507.task_manager_mobile.utils.TokenManager
import com.nurlan1507.task_manager_mobile.utils.WindowSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "CoroutineCreationDuringComposition"
)

@Composable
fun MainScreen(
    navController: NavController,
    windowSize: WindowSize,
    tasksViewModel: TasksViewModel,
    projectViewmodel: ProjectViewmodel,
    userViewModel: UserViewModel
) {
    val ctx = LocalContext.current as Activity
    val taskState = tasksViewModel.tasksState
    val projectState = projectViewmodel.projectState.value
    var showDialog by remember { mutableStateOf(false) }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if (it == ModalBottomSheetValue.Hidden) {
                tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(null))
            }
            true
        },
        skipHalfExpanded = true,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing)
    )
    val modalSheetState2 = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            true
        },
        skipHalfExpanded = true,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing)
    )


    val scope = rememberCoroutineScope()
    val showBottomSheet: () -> Unit = {
        scope.launch {
            modalSheetState.show()
        }
    }

    val hideBottomSheet: () -> Unit = {
        scope.launch {
            modalSheetState.hide()
        }
    }

    val hideSecondBottomSheet: () -> Unit = {
        scope.launch {
            modalSheetState2.hide()
        }
    }
    LaunchedEffect(projectState.projectList) {
        tasksViewModel.onEvent(TasksEvent.GetTasks(1))
    }
    BackHandler {
        if (modalSheetState2.isVisible) {
            hideSecondBottomSheet()
        } else if (modalSheetState.isVisible) {
            hideBottomSheet()
        } else {
            ctx.finish()
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar<String>(title = taskState.value.currentCategory.title) {

            }
        },
        bottomBar = {
            BottomNavigationBar(
                showAddTask = {
                    scope.launch {
                        tasksViewModel.onEvent(
                            TasksEvent.ChangeBottomSheetDestination(
                                BottomSheetLayoutType.AddTask
                            )
                        )
                        showBottomSheet()
                    }
                },
                showSearch = {
                    scope.launch {
                        tasksViewModel.onEvent(
                            TasksEvent.ChangeBottomSheetDestination(
                                BottomSheetLayoutType.Search
                            )
                        )
                        showBottomSheet()
                    }
                },
                showProfile = {
                    scope.launch {
                        tasksViewModel.onEvent(
                            TasksEvent.ChangeBottomSheetDestination(
                                BottomSheetLayoutType.Profile
                            )
                        )
                        showBottomSheet()

                    }
                },
                showNotification = {
                    scope.launch {
                        tasksViewModel.onEvent(
                            TasksEvent.ChangeBottomSheetDestination(
                                BottomSheetLayoutType.Nofifications
                            )
                        )
                        showBottomSheet()
                    }
                }
            )
        }
    ) {
        if (showDialog) {
            CustomAlertDialog(
                onConfirm = {
                    showDialog = false
                    tasksViewModel.onEvent(TasksEvent.ClearTextFieldState)
                    hideBottomSheet()
                    tasksViewModel.error.value = false
                },
                onDismiss = {
                    showDialog = false
                    scope.launch {
                        tasksViewModel.onEvent(
                            TasksEvent.ChangeBottomSheetDestination(
                                BottomSheetLayoutType.AddTask
                            )
                        )
                        showBottomSheet()
                        tasksViewModel.error.value = false
                    }
                }
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(taskState.value.tasks) { task ->

                IncomeTaskView(
                    taskWithProject = task,
                    onDeleteButtonClicked = {
                        scope.launch {
                            tasksViewModel.onEvent(TasksEvent.DeleteTask(task.task))
                        }
                    }
                )
            }
        }

        ModalBottomSheetLayout(
            sheetContent = {
                Column(modifier = Modifier.heightIn(min = 1.dp)) {
                    when (tasksViewModel.currentBottomSheetLayout.value) {
                        is BottomSheetLayoutType.Profile -> {
                            Log.d("currentDestination", "main")
                            MainBottomSheetLayout(
                                tasksViewModel, modalSheetState
                            )
                        }

                        is BottomSheetLayoutType.Nofifications -> {
                            Log.d("currentDestination", "notifications")
                            MainBottomSheetLayout(
                                tasksViewModel, modalSheetState

                            )
                        }

                        is BottomSheetLayoutType.Search -> {
                            Log.d("currentDestination", "search")
                            MainBottomSheetLayout(
                                tasksViewModel, modalSheetState
                            )
                        }

                        is BottomSheetLayoutType.AddTask -> {
                            Log.d("currentDestination", "add_task")
                            TaskCreationBottomSheetLayout(
                                tasksViewModel = tasksViewModel,
                                sheetState = modalSheetState2,
                                navController = navController
                            )
                        }

                        else -> {
                            Box {}
                        }
                    }
                }
            },
            sheetState = modalSheetState,
        ) {}
        ModalBottomSheetLayout(
            sheetContent = { DateSelectionBottomSheetLayout(tasksViewModel = tasksViewModel) },
            sheetState = modalSheetState2
        ) {

        }
    }
}
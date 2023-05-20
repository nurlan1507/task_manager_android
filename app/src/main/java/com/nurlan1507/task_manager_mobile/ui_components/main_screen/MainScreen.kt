package com.nurlan1507.task_manager_mobile.ui_components.main_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.DateSelectionBottomSheetLayout
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.MainBottomSheetLayout
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts.TaskCreationBottomSheetLayout
import com.nurlan1507.task_manager_mobile.global_components.BottomNavigationBar
import com.nurlan1507.task_manager_mobile.ui_components.TopBar
import com.nurlan1507.task_manager_mobile.ui_components.draggable.DragTarget
import com.nurlan1507.task_manager_mobile.ui_components.draggable.DropItem
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.components.CustomAlertDialog
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.components.IncomeTaskView
import com.nurlan1507.task_manager_mobile.utils.WindowSize
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class
)
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "CoroutineCreationDuringComposition"
)

@Composable
fun MainScreen(
    navController: NavController,
    windowSize: WindowSize,
    tasksViewModel: TasksViewModel = hiltViewModel(),
    projectViewmodel: ProjectViewmodel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current as Activity
    val taskState = tasksViewModel.tasksState.value
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
    val tasksByDate = remember {
        mutableStateOf(mapOf<TasksViewModel.TaskDate, List<TaskWithProject>>())
    }
    LaunchedEffect(projectState.currentProject) {
        tasksViewModel.onEvent(TasksEvent.GetTasks(1))
    }
    tasksByDate.value = taskState.tasks.groupBy {
        tasksViewModel.getDateCategory(it.task.finishDate ?: 0)
    }
    val lazyListState = rememberLazyListState()
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
            TopBar()
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
        //for dragging
//        var isDragging by remember { mutableStateOf(false) }
//        var dragOffset by remember { mutableStateOf(0F) }
//        var dropZoneColor by remember { mutableStateOf(Color.Transparent) }
//
//        val calculatedOffset by remember { mutableStateOf<Float?>(null) }
//
//        val state = rememberReorderableLazyListState(onMove = { from, to ->
//            tasksViewModel.tasksState.value = tasksViewModel.tasksState.value.copy(
//                tasks = tasksViewModel.tasksState.value.tasks.toMutableList().apply {
//                    add(to.index, removeAt(from.index))
//                })
//        })

//        Column() {
//            LazyColumn(){
//                items(tasksViewModel.tasksState.value.tasks){item->
//                    DragTarget(modifier =Modifier  , dataToDrop = item) {
//                        IncomeTaskView(
//                            modifier =Modifier ,
//                            deletedTask = taskState .deletedTask,
//                            taskWithProject = item,
//                            onDeleteButtonClicked = {
//                                tasksViewModel.onEvent(TasksEvent.DeleteTask(it))
//                            }
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//            DropItem<TaskWithProject>(modifier = Modifier.size(100.dp))
//            {isBound,data ->
//                if(isBound==true){
//                    Box(modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Red))
//            }else{
//                    Box(modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Red))
//                }
//        }
        Column(modifier = Modifier
            .padding(it)
        ) {
            TasksBlock(tasksMap = tasksByDate.value)
        }
    }
    ModalBottomSheetLayout(
        sheetContent = {
            Column(modifier = Modifier.heightIn(min = 1.dp)) {
                when (tasksViewModel.currentBottomSheetLayout.value) {
                    is BottomSheetLayoutType.Profile -> {
                        Log.d("currentDestination", "main")
                        MainBottomSheetLayout(
                            navController, tasksViewModel, modalSheetState
                        )
                    }

                    is BottomSheetLayoutType.Nofifications -> {
                        Log.d("currentDestination", "notifications")
                        MainBottomSheetLayout(
                            navController, tasksViewModel, modalSheetState
                        )
                    }

                    is BottomSheetLayoutType.Search -> {
                        Log.d("currentDestination", "search")
                        MainBottomSheetLayout(
                            navController, tasksViewModel, modalSheetState
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


@Composable
fun TasksBlock(tasksMap: Map<TasksViewModel.TaskDate, List<TaskWithProject>>) {
    LazyColumn {
        items(tasksMap.entries.toList()) { (taskDate, taskList) ->
            TaskBlock(taskDate, taskList)
        }
    }
}

@Composable
fun TaskBlock(taskDate: TasksViewModel.TaskDate, taskList: List<TaskWithProject>) {
    Text(text = taskDate.name)
    taskList.forEach { task ->
        IncomeTaskView(
            modifier = Modifier,
            taskWithProject =task ,
            onDeleteButtonClicked = {},
            deletedTask = null
        )
    }
}

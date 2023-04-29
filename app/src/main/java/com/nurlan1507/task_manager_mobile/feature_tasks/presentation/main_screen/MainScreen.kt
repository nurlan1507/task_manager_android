package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.BottomSheetLayoutState
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts.DateSelectionBottomSheetLayout
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts.MainBottomSheetLayout
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts.TaskCreationBottomSheetLayout
import com.nurlan1507.task_manager_mobile.global_components.BottomNavigationBar
import com.nurlan1507.task_manager_mobile.global_components.TopBar
import com.nurlan1507.task_manager_mobile.utils.WindowSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "CoroutineCreationDuringComposition"
)

@Composable
fun MainScreen(navController: NavController,windowSize: WindowSize, tasksViewModel: TasksViewModel){
    val ctx = LocalContext.current as Activity
    val state = tasksViewModel.tasksState
    var showDialog by remember{ mutableStateOf(false) }


    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if(it == ModalBottomSheetValue.Hidden){
                tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(null ))
            }
            true
        },
        skipHalfExpanded = true
    )
    val sheetState2 = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if(it == ModalBottomSheetValue.Hidden){
                tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(null ))
            }
            true
        },
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    BackHandler {
        if(sheetState2.isVisible){
            scope.launch {
                sheetState2.hide()
            }
        }
        else if(sheetState.isVisible) {
            scope.launch {
                sheetState.hide()
            }
        }else{
            ctx.finish()
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar<String>(title = state.value.currentCategory.title){

            }
        },
        content = {
            Column() {
                if(showDialog){
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text(text = "Вы уверены, что хотите закрыть?", style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)) },
                        text = { Text(text = "Несохраненные данные удалятся", style = MaterialTheme.typography.body1)},
                        confirmButton = {
                            TextButton(onClick = { showDialog = false ; tasksViewModel.onEvent(TasksEvent.ClearTextFieldState()); scope.launch {sheetState.hide()};}) {
                                Text(text = "Да", color = Color(0xFF5E97FF))
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {showDialog = false; scope.launch {
                                tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.AddTask ))
                                sheetState.show()
                            } }) {
                                Text(text = "Нет", color = Color(0xFF5E97FF))
                            }
                        }
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                showAddTask ={
                    scope.launch {
                        tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.AddTask))
                        sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(500))
                    } } ,
                showSearch = {
                    scope.launch {
                        tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.Search))
                        sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(500))
                    }},
                showProfile = {
                    scope.launch {
                        tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.Profile))
                        sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(500))
                    }},
                showNotification = {
                    scope.launch {
                        tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.Nofifications))
                        sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(500))
                    }}
            )
        }
    )


    ModalBottomSheetLayout(
        sheetContent ={
            Column(modifier =
            Modifier.heightIn(min = 1.dp)) {
                when(tasksViewModel.currentBottomSheetLayout.value){
                    is BottomSheetLayoutType.Profile -> {
                        Log.d("currentDestination", "main")
                        MainBottomSheetLayout(
                            tasksViewModel = tasksViewModel,
                            sheetState = sheetState
                        )
                    }
                    is BottomSheetLayoutType.Nofifications ->{
                        Log.d("currentDestination", "notifications")
                        MainBottomSheetLayout(
                            tasksViewModel = tasksViewModel,
                            sheetState = sheetState
                        )
                    }
                    is BottomSheetLayoutType.Search-> {
                        Log.d("currentDestination", "search")
                        MainBottomSheetLayout(
                            tasksViewModel = tasksViewModel,
                            sheetState = sheetState
                        )
                    }
                    is BottomSheetLayoutType.AddTask ->{
                        Log.d("currentDestination", "add_task")
                        TaskCreationBottomSheetLayout(tasksViewModel = tasksViewModel, sheetState = sheetState2) {
                            if (it.currentValue != ModalBottomSheetValue.Hidden) {
                                if (tasksViewModel.fieldState.value.title.isNotEmpty() || tasksViewModel.fieldState.value.description.isNotEmpty()) {
                                    Log.d("ModalBottomShit", "sdsd")
                                    showDialog = true
                                }

                            }
                        }
                    }
                    else -> {Box{}}
                }
            }
        } ,
        sheetState = sheetState
        ){}


    ModalBottomSheetLayout(
        sheetContent ={
            Column(modifier =
            Modifier.heightIn(min = 1.dp)) {
                DateSelectionBottomSheetLayout(tasksViewModel = tasksViewModel)
            }
        } ,
        sheetState = sheetState2
    ){}
}
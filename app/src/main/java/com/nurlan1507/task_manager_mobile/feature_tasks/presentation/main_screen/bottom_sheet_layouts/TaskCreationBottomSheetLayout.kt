package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts

import android.util.Log
import android.widget.Space
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.BottomSheetLayoutState
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.MyExpandableTextField
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.ProjectSelectionButton
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.TaskCreationButton
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.BottomSheetLayoutType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TaskCreationBottomSheetLayout(tasksViewModel: TasksViewModel, sheetState: ModalBottomSheetState, onClose:(ModalBottomSheetState)->Unit){
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val tasksState = tasksViewModel.tasksState
    val keyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(sheetState){
        keyboard?.hide()
        scope.launch {
            delay(100) // Make sure you have delay here
        }
    }
    DisposableEffect(Unit){
        onDispose {
            Log.d("laynchEffect", "taskCreateLauncheffect")
            onClose(sheetState)
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(vertical = 5.dp, horizontal = 5.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            softwareKeyboardController?.hide()
                        }
                    },
                value =tasksViewModel.fieldState.value.title,
                onValueChange = {tasksViewModel.onEvent(TasksEvent.EnteredTitle(it))},
                placeholder = { Text("It is a long established fact that a reader will b", style = MaterialTheme.typography.h6 , fontWeight = FontWeight.Light) },
                textStyle = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            softwareKeyboardController?.hide()
                        }
                    },
                keyboardActions = KeyboardActions(onDone = {
                    softwareKeyboardController?.hide()
                }),
                value = tasksViewModel.fieldState.value.description,
                onValueChange = {
                    tasksViewModel.onEvent(TasksEvent.EnteredDescription(it))
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text("Описание", style = MaterialTheme.typography.body1 , fontWeight = FontWeight.Light) },
                textStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
            )
            Row(modifier = Modifier
                .wrapContentHeight()
                .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                TaskCreationButton(icon = Icons.Default.DateRange, text = "Срок выполнения", onClick ={
//                    tasksViewModel.onEvent(TasksEvent.ChangeBottomSheetDestination(BottomSheetLayoutType.DateSelection))
//                    scope.launch {
//                        sheetState.animateTo(ModalBottomSheetValue.Expanded, tween(500))
//                    }
                    scope.launch {
                        sheetState.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                    }
                })
                TaskCreationButton(icon =Icons.Default.DateRange , text = "Приоритет")
                TaskCreationButton(icon =Icons.Default.DateRange , text = "Напоминание")
                TaskCreationButton(icon =Icons.Default.DateRange , text = "чето еще")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp), horizontalArrangement = Arrangement.SpaceBetween){
                ProjectSelectionButton(projectId = tasksViewModel.fieldState.value.projectId, projectList = tasksState.value.projects )
                Box(modifier = Modifier
                    .background(Color(0xFF5E97FF))
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .clickable {

                    },

                ) {
                    Icon(Icons.Default.Send,"create", tint = Color.White, modifier = Modifier.align(Alignment.Center))
                }
            }
        }




    }
}
package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.MyExpandableTextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCreationBottomSheetLayout(tasksViewModel: TasksViewModel, sheetState: ModalBottomSheetState){
    val state = tasksViewModel.tasksState

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()){
        Column(modifier = Modifier.fillMaxWidth()) {
            MyExpandableTextField(value = state.value.task.title, onValueChange = )
        }


    }
}
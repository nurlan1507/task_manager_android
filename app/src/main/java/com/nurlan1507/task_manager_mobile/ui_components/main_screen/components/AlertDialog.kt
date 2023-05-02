package com.nurlan1507.task_manager_mobile.ui_components.main_screen.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.BottomSheetLayoutType
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomAlertDialog(onConfirm:()->Unit,onDismiss:()->Unit){
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Вы уверены, что хотите закрыть?", style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)) },
        text = { Text(text = "Несохраненные данные удалятся", style = MaterialTheme.typography.body1) },
        confirmButton = {
            TextButton(onClick = { onConfirm()}) {
                Text(text = "Да", color = Color(0xFF5E97FF))
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismiss()}) {
                Text(text = "Нет", color = Color(0xFF5E97FF))
            }
        },
        properties = DialogProperties(decorFitsSystemWindows = true)
    )
}
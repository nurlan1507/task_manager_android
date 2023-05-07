package com.nurlan1507.task_manager_mobile.ui_components.main_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun IncomeTaskView(taskWithProject: TaskWithProject, onDeleteButtonClicked: (Task) -> Unit) {
    val date =
        if (taskWithProject.task.finishDate != null) Date(taskWithProject.task.finishDate * 1000L)
        else "Без даты"
    val isVisible = remember {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        modifier = Modifier.wrapContentSize(),
        visible = isVisible.value,
        enter = expandVertically(
            animationSpec = tween(200)
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(300)
        )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) {
                scope.launch {
                    isVisible.value = !isVisible.value
                    delay(500)
                    onDeleteButtonClicked(taskWithProject.task)
                }
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(y = 4.dp)
                        .background(Color(0xFFA0BCF0), shape = CircleShape)
                        .border(width = 2.dp, color = Color(0xFF5E97FF), shape = CircleShape)
                        .clickable {
                            onDeleteButtonClicked(taskWithProject.task)
                        }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column() {
                    Text(
                        text = taskWithProject.task.title.toString(),
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = taskWithProject.task.description.toString(),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Light
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "15 мая",
                            style = MaterialTheme.typography.body2.copy(color = Color(0xFF5E97FF))
                        )
                    }
                }
            }

        }
    }
}


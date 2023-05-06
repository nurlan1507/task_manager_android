package com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectEvent
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.MainScreenNavigationOption
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomSheetLayout(taskViewModel: TasksViewModel,sheetState: ModalBottomSheetState) {
    val navigationOptions = listOf(
        MainScreenNavigationOption.TodayTasks,
        MainScreenNavigationOption.UpcomingTasks,
        MainScreenNavigationOption.Filter
    )
    var showProjects by remember { mutableStateOf(false) }
    val state = taskViewModel.tasksState
    val scope = rememberCoroutineScope()
    val hideSheet = {
        scope.launch {
            sheetState.hide()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(15.dp)
                .height(5.dp)
                .background(color = Color.Gray)
                .clip(RoundedCornerShape(5))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp)
            ) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50))
                            .background(color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold),
                            text = "Имя пользователя",

                        )
                        Text(
                            text = "почта пользователя@mail",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(Icons.Default.Settings, "Settings", tint = Color.Black)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                navigationOptions.map {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(if (it == state.value.currentCategory) Color.LightGray else Color.Transparent)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            taskViewModel.onEvent(TasksEvent.ChangeCategory(it))
                            hideSheet()
                        }) {

                        Box(modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()){
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()){
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = "Google",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(start = 4.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = it.title, style = MaterialTheme.typography.body1)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Проекты", style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold),)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Использовано 1/5",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Light),
                        modifier = Modifier.background(color = Color.LightGray)
                    )
                }
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Add, "create a project")
                    }
                    IconButton(onClick = { showProjects = !showProjects }) {
                        Icon(
                            imageVector = if (showProjects == false) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            "show projects"
                        )
                    }
                }
            }
                Column {
                    AnimatedVisibility(
                        visible = showProjects,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                }) {
                                Box(modifier = Modifier
                                    .padding(horizontal = 24.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth()){
                                    Row(modifier = Modifier.align(Alignment.CenterStart)) {
                                        Canvas(modifier = Modifier.size(20.dp)) {
                                            drawCircle(
                                                color = Color.Gray,
                                                center = Offset(size.width / 2, size.height / 2),
                                                radius = size.width / 2
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(15.dp))
                                        Text(text = "Работа", style = MaterialTheme.typography.body1)
                                    }
                                }
                            }

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(horizontal = 20.dp)

                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                }) {
                                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Google",
                                        tint = Color.Black
                                    )
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "Настройка проектов",
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }

                }

        }

    }
}
